/***************************** BEGIN LICENSE BLOCK ***************************

The contents of this file are subject to the Mozilla Public License, v. 2.0.
If a copy of the MPL was not distributed with this file, You can obtain one
at http://mozilla.org/MPL/2.0/.

Software distributed under the License is distributed on an "AS IS" basis,
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
for the specific language governing rights and limitations under the License.
 
Copyright (C) 2012-2015 Sensia Software LLC. All Rights Reserved.
 
******************************* END LICENSE BLOCK ***************************/

package org.sensorhub.impl.sensor.rtpcam;

import org.sensorhub.api.config.DisplayInfo;


/**
 * <p>
 * RTP/RTSP configuration parameters
 * </p>
 *
 * @author Alex Robin <alex.robin@sensiasoftware.com>
 * @since Dec 12, 2015
 */
public class RTSPConfig
{    
    @DisplayInfo(label="Remote RTSP Port", desc="Remote TCP port where RTSP commands are sent")
    public int rtspPort;
    
    @DisplayInfo(label="RTSP Video Path", desc="Video path to request from RTSP server")
    public String videoPath;
    
    @DisplayInfo(label="Local UDP Port", desc="Local UDP port for receiving RTP packets")
    public int localUdpPort;
}