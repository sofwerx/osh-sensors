package org.sensorhub.impl.sensor.plume;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>Title: PlumeInputWriter.java</p>
 * <p>Description: </p>
 *
 * @author T
 * @date Jan 15, 2016
 * 
 */
public class PlumeInputWriter {

	public static final String INPUT_HEADER = "&PPDM_INPUT\n";
	
	public void writeInputFile(Path p, PlumeInputRecord rec) throws IOException {
		// assert allRequiredFieldsSet
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(p.toFile())) ) {
			writer.write(INPUT_HEADER);
			writer.write("title='" + rec.title + "',\n");
			writer.write("IYEAR1 = " + rec.startYear + ",\n");
			writer.write("IMONTH1 = " + rec.startMonth+ ",\n");
			writer.write("IDATE1 = " + rec.startDay + ",\n");
			writer.write("ITIME1 = " + rec.startHourMinute + ",\n");
			writer.write("NGRID = " + rec.gridNum + ",\n");
			writer.write("\n");
			writer.write("AFILPRX = " + rec.ramsFilePrefix + ",\n");
			writer.write("TIMMAX = " + rec.durationHours + ",\n");
			writer.write("IDTMOV = " + rec.modelTimeStepSeconds + ",\n");
			writer.write("IDTIN = " + rec.metInputFreqSeconds + ",\n");
			writer.write("IDTOUT = " + rec.modelOutputFreqSeconds + ",\n");
			writer.write("ITYPREL = " + rec.releaseType + ",\n");
			writer.write("NSORCE = " + rec.numSources + ",\n");
			writer.write("ITURB = " + rec.turbulence + ",\n");
			writer.write("IPLRS = " + rec.plumeRise + ",\n");
			writer.write("ISCOOR = " + rec.inputCoordRefSystem + ",\n");
			writer.write("IPCOOR = " + rec.outputCoordRefSystem + ",\n");
			writer.write("RELDUR = " + rec.releaseDurationHours + ",\n");
			writer.write("TDELAY = " + rec.delayTimeSeconds + ",\n");
			writeArray(writer, "SLON", rec.sourceLon);
			writeArray(writer, "SLAT", rec.sourceLat);
			writeArray(writer, "STKZ", rec.stackHeight);
			writeArray(writer, "QS", rec.buoyancyFlux);
		}
	}
	
	private void writeArray(BufferedWriter writer, String varName, double [] vals) throws IOException {
		writer.write(varName + " = " );
		for(double v: vals) {
			writer.write(v + ",");
		}
		writer.write("\n");
	}
	
	public static void main(String[] args) throws Exception {
		Path outPath = Paths.get("C:/Users/tcook/root/sensorHub/plume/ppdinTest.txt");
		PlumeInputRecord rec = new PlumeInputRecord();
		PlumeInputWriter writer = new PlumeInputWriter();
		writer.writeInputFile(outPath, rec);
	}
}
