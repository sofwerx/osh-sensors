/***************************** BEGIN LICENSE BLOCK ***************************

The contents of this file are subject to the Mozilla Public License, v. 2.0.
If a copy of the MPL was not distributed with this file, You can obtain one
at http://mozilla.org/MPL/2.0/.

Software distributed under the License is distributed on an "AS IS" basis,
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
for the specific language governing rights and limitations under the License.
 
The Initial Developer is Botts Innovative Research Inc. Portions created by the Initial
Developer are Copyright (C) 2014 the Initial Developer. All Rights Reserved.
 
******************************* END LICENSE BLOCK ***************************/

package org.sensorhub.impl.sensor.intelipod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.sensorhub.api.comm.ICommProvider;
import org.sensorhub.api.common.SensorHubException;
import org.sensorhub.api.sensor.SensorException;
import org.sensorhub.impl.sensor.AbstractSensorModule;
import org.sensorhub.impl.sensor.intelipod.IntelipodOutput;
import org.sensorhub.impl.sensor.intelipod.IntelipodConfig;
import org.vast.sensorML.SMLHelper;


/**
 * <p>
 * Driver implementation supporting the Laser Technology TruPulse 360 Laser Rangefinder.
 * The TruPulse 360 includes GeoSpatial Orientation ("azimuth"), as well as inclination
 * and direct distance. When combined with a sensor that measures GPS location of the 
 * TruPulse sensor, one can calculate the geospatial position of the target.
 * </p>
 *
 * @author Mike Botts <mike.botts@botts-inc.com>
 * @since June 8, 2015
 */
public class IntelipodSensor extends AbstractSensorModule<IntelipodConfig>
{
    ICommProvider<?> commProvider;
    IntelipodOutput intelipodOut;
    BufferedReader dataIn;
    BufferedWriter dataOut;
    BufferedWriter writer;
    BufferedReader input;
    OutputStream output;
    String inputLine = null;
    boolean started = false;
    
    
    @Override
    public void init() throws SensorHubException
    {
    	super.init();
        
    	// generate identifiers: use serial number from config or first characters of local ID
        generateUniqueID("urn:venti:intelipod:", config.serialNumber);
        generateXmlID("INTELIPOD_", config.serialNumber);
        
        // init comm provider
        if (commProvider == null)
        {
            // we need to recreate comm provider here because it can be changed by UI
            if (config.commSettings == null)
                throw new SensorHubException("No communication settings specified");
            commProvider = config.commSettings.getProvider();
            commProvider.start();
            
            // connect to comm data streams
            try
            {
            	dataIn = new BufferedReader(new InputStreamReader(commProvider.getInputStream()));
                //dataOut = new BufferedWriter(new OutputStreamWriter(commProvider.getOutputStream()));
                getLogger().info("Connected to Intelipod data stream");
                
            }
            catch (IOException e)
            {
                throw new SensorException("Error while initializing communications ", e);
            }
        }

        // init main data interface
        intelipodOut = new IntelipodOutput(this);
        addOutput(intelipodOut, false);
        intelipodOut.init();
    }


    @Override
    protected void updateSensorDescription()
    {
        synchronized (sensorDescLock)
        {
        	if (!sensorDescription.isSetDescription())
                sensorDescription.setDescription("Venti Intelipod Sensor");
          
        	SMLHelper helper = new SMLHelper(sensorDescription);
        	helper.addShortName("Intellipod" + (config.serialNumber != null ? " " + config.serialNumber : ""));
        	helper.addManufacturerName("Venti");
            if (config.modelNumber != null)
        	    helper.addModelNumber(config.modelNumber);
        	if (config.serialNumber != null)
                helper.addSerialNumber(config.serialNumber);
        }
    }
    
    
    @Override
    public void start() throws SensorHubException
    {
    	// start main measurement thread
        Thread t = new Thread(new Runnable()
        {
            public void run()
            {
                while (started)
                {
                    getMeasurement();
                }
                
                dataIn = null;
            }
        });
        
        started = true;
        t.start();
    }
    

    @Override
    public void stop() throws SensorHubException
    {
    	//close();
    	started = false;
        
        if (dataIn != null)
        {
            try { dataIn.close(); }
            catch (IOException e) { }
        }
        
        if (commProvider != null)
        {
        	try {
  				commProvider.stop();
  			} catch (SensorHubException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
        	commProvider = null;
        }
    }
    
    
    private void getMeasurement()
    {	
    	String inputLine = null;
    	try
    	{
            inputLine = dataIn.readLine();
            intelipodOut.postMeasurement(inputLine);
		}
    	catch (Exception e)
    	{
			e.printStackTrace();
		}
    }
    

    @Override
    public void cleanup() throws SensorHubException
    {
    	
    }
    
    
    @Override
    public boolean isConnected()
    {
        return true;
    }
}