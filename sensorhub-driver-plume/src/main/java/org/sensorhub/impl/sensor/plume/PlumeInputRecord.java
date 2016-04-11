	package org.sensorhub.impl.sensor.plume;

/**
 * <p>Title: PlumeInputRecord.java</p>
 * <p>Description: </p>
 *
 * @author T
 * @date Feb 3, 2016
 * 
 * 
 
 &PPDM_INPUT
 title='HUNTSVILLE SIMULATION',
 IYEAR1 =  2015,
 IMONTH1=  12,
 IDATE1 =  13,
 ITIME1 =  1400,
 NGRID  =  1,
 
 AFILPRX='/rgroup/land/supmod/ALFIRES/rams6.0/anal/AL',
 TIMMAX = 10.,
 IDTMOV =   60,
 IDTIN  =  1200,
 IDTOUT =  600,
 ITYPREL=  2,
 NSORCE =  1,
 ITURB  =  1,
 IPLRS  =  1,
 ISCOOR =  2,
 IPCOOR =  2,
 RELDUR =  2.,
 TDELAY =  0., 0., 3600.,6300.,12600.,3600.,3600.,7200.,7200.,7200.,
 SLON     = -86.580362,-86.7214,-86.6358,-87.5297,-87.7190,-88.5,-89.0,-88.5,-89.0,-86.0,      !source locations
 SLAT     =  34.690624, 34.5748, 34.5388, 33.5785, 33.5050, 33.7, 33.7, 34.0, 34.0, 34.0,
 STKZ   = 10.,50.,50.,50.,50,50., 50.,100.,100.,100,
 SQ     = 100., 150.,20.,0.,0., 0.,0., 0.,0.,0.,
/
	 
!   IMONTH1  = 04,
!   IDATE1   = 18,
!   IYEAR1   = 2007,
!   ITIME1   = 1200,
!   NGRID =  1,                  !grid no. for RAMS simulation to be used
!                                !=0&PPDM_INPUT
 title='HUNTSVILLE SIMULATION',
 IYEAR1 =  2015,
 IMONTH1=  12,
 IDATE1 =  13,
 ITIME1 =  1400,
 NGRID  =  1,
 
 AFILPRX='/rgroup/land/supmod/ALFIRES/rams6.0/anal/AL',
 TIMMAX = 10.,
 IDTMOV =   60,
 IDTIN  =  1200,
 IDTOUT =  600,
 ITYPREL=  2,
 NSORCE =  1,
 ITURB  =  1,
 IPLRS  =  1,
 ISCOOR =  2,
 IPCOOR =  2,
 RELDUR =  2.,
 TDELAY =  0., 0., 3600.,6300.,12600.,3600.,3600.,7200.,7200.,7200.,
 SLON     = -86.580362,-86.7214,-86.6358,-87.5297,-87.7190,-88.5,-89.0,-88.5,-89.0,-86.0,      !source locations
 SLAT     =  34.690624, 34.5748, 34.5388, 33.5785, 33.5050, 33.7, 33.7, 34.0, 34.0, 34.0,
 STKZ   = 10.,50.,50.,50.,50,50., 50.,100.,100.,100,
 SQ     = 100., 150.,20.,0.,0., 0.,0., 0.,0.,0.,
/
	 
!   IMONTH1  = 04,
!   IDATE1   = 18,
!   IYEAR1   = 2007,
!   ITIME1   = 1200,
!   NGRID =  1,                  !grid no. for RAMS simulation to be used
!                                !=0 uses all grids; otherwise only one grid is used
!
! AFILPRX: RAMS file prefix (for rams6.0/anal/AL-A-2015-09-16-000000-g2.h5 it's 
!          AFILPRX='rams6.0/anal/AL')
! ITURB  : switch of tubulence effect (0=off ; 1=on)

 
! TIMMAX =  11.,                 !R, simulation duration, in [hr]
! IDTMOV =  60,                  !I, model timestep, in [sec]
! IDTIN  =  900,                 !I, met input  frequency, in [sec]
! IDTOUT =  900,                 !I, model output freguency, in [sec]
! NSORCE : # of sources(point sources)
! ITYPREL=  1,                   !=1, continuous plume; =2 puff release
! ITURB  =  1,                   !=0: use LES winds; =1:use statistic winds
! IPLRS  =  0,                   !=0, no plume rise (@stack ht); =1 PR calc.
! ISCOOR =  2,                   !=1, SLON,SLAT are long. lat. deg; =2, are (x,y) (RAMS Pole center is (0,0)) in [m]
! IPCOOR =  2,                   !=1, output in (lon,lat,z); =2 in (x,y,z) in [m]
! SLON   = -86.75,-86.6,...       !
! SLAT   =  32.96, 32.80,...      !
! STKZ   = 15., 30.,1,1,1,1,....  ! 
! QS     = 1.e1, 1.e1,...         !buoyancy flux
!
! AFILPRX: RAMS file prefix (for rams6.0/anal/AL-A-2015-09-16-000000-g2.h5 it's 
!          AFILPRX='rams6.0/anal/AL')
! ITURB  : switch of tubulence effect (0=off ; 1=on)

 
 */
public class PlumeInputRecord {
	String title = "";
		
	int startMonth;
	int startDay;
	int startYear;
	String startHourMinute = "0000";  // [hhmm] format
	int gridNum;  // 0 uses all grids; otherwise only one grid is used
	
	String ramsFilePrefix;
	
	int turbulence;
	
	double durationHours;
	int modelTimeStepSeconds;  //  met input frequency in [sec], it has to be consistent with RAMS output frequency. e.g. when 
	  						   //  RAMS output freq is every 10min, then IDTIN can be 600/1200/1800/etc but not 900 sec.
	int metInputFreqSeconds;
	int modelOutputFreqSeconds;
	
	int numSources;  // can not exceed 10
	int releaseType; // 1, continuous plume; =2 puff release
	int releaseDurationHours;
	int turbulenceType;  // =0: use LES winds; =1:use statistic winds
	int plumeRise;  // =0, no plume rise (@stack ht); =1 PR calc.
	
	// ISCOOR=1, the input SLON and SLAT are in (x,y) [m] and the (0,0) is the POLELON and POLELAT set in RAMSIN
    // ISCOOR=2, the input SLON and SLAT are in (lon, lat)
    int inputCoordRefSystem;
    // IPCOOR=1, the output particle positions are in (x,y) [m] and the (0,0) is the POLELON and POLELAT set in RAMSIN
    // IPCOOR=2, the output particle positions are in (lon, lat)
	int outputCoordRefSystem;
	int delayTimeSeconds; // time (in [sec]) between the starting release from a source and the particle model starting time
	double [] sourceLat = new double[10];  // declared arrays of size 10. You have to give 10 numbers
	double [] sourceLon = new double[10];
	double [] stackHeight = new double[10];
	double [] buoyancyFlux = new double[10];
}
