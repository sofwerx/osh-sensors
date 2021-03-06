package org.sensorhub.impl.sensor.waterdata;

public final class WaterML2 {
    public final String name;
    public final String declaredType;
    public final String scope;
    public final Value value;
    public final boolean nil;
    public final boolean globalScope;
    public final boolean typeSubstituted;

    public WaterML2(String name, String declaredType, String scope, Value value, boolean nil, boolean globalScope, boolean typeSubstituted){
        this.name = name;
        this.declaredType = declaredType;
        this.scope = scope;
        this.value = value; // Level 1 Instance
        this.nil = nil;
        this.globalScope = globalScope;
        this.typeSubstituted = typeSubstituted;
    }

    // Level 1 Class Declaration
    public static final class Value {
        //public final QueryInfo queryInfo;
        public final TimeSery timeSeries[];

//        public Value(QueryInfo queryInfo, TimeSery[] timeSeries){
//            this.queryInfo = queryInfo;
//            this.timeSeries = timeSeries;
//        }
        
        public Value(TimeSery[] timeSeries){
            //this.queryInfo = queryInfo;
            this.timeSeries = timeSeries;
        }

//        public static final class QueryInfo {
//            public QueryInfo(){
//            }
//        }

        // Class for timeSeries
        public static final class TimeSery {
            public final SourceInfo sourceInfo;
            public final Variable variable;
            public final Values values[];
            public final String name;
    
            public TimeSery(SourceInfo sourceInfo, Variable variable, Values[] values, String name){
                this.sourceInfo = sourceInfo;
                this.variable = variable;
                this.values = values;
                this.name = name;
            }
    
            public static final class SourceInfo {
                public final String siteName;
                public final SiteCode siteCode[];
                public final TimeZoneInfo timeZoneInfo;
                public final GeoLocation geoLocation;
                public final Note note[];
                public final SiteType siteType[];
                public final SiteProperty siteProperty[];
        
                public SourceInfo(String siteName, SiteCode[] siteCode, TimeZoneInfo timeZoneInfo, GeoLocation geoLocation, Note[] note, SiteType[] siteType, SiteProperty[] siteProperty){
                    this.siteName = siteName;
                    this.siteCode = siteCode;
                    this.timeZoneInfo = timeZoneInfo;
                    this.geoLocation = geoLocation;
                    this.note = note;
                    this.siteType = siteType;
                    this.siteProperty = siteProperty;
                }
        
                public static final class SiteCode {
                    public final String value;
                    public final String network;
                    public final String agencyCode;
            
                    public SiteCode(String value, String network, String agencyCode){
                        this.value = value;
                        this.network = network;
                        this.agencyCode = agencyCode;
                    }
                }
        
                public static final class TimeZoneInfo {
                    public final DefaultTimeZone defaultTimeZone;
                    public final DaylightSavingsTimeZone daylightSavingsTimeZone;
                    public final boolean siteUsesDaylightSavingsTime;
            
                    public TimeZoneInfo(DefaultTimeZone defaultTimeZone, DaylightSavingsTimeZone daylightSavingsTimeZone, boolean siteUsesDaylightSavingsTime){
                        this.defaultTimeZone = defaultTimeZone;
                        this.daylightSavingsTimeZone = daylightSavingsTimeZone;
                        this.siteUsesDaylightSavingsTime = siteUsesDaylightSavingsTime;
                    }
            
                    public static final class DefaultTimeZone {
                        public final String zoneOffset;
                        public final String zoneAbbreviation;
                
                        public DefaultTimeZone(String zoneOffset, String zoneAbbreviation){
                            this.zoneOffset = zoneOffset;
                            this.zoneAbbreviation = zoneAbbreviation;
                        }
                    }
            
                    public static final class DaylightSavingsTimeZone {
                        public final String zoneOffset;
                        public final String zoneAbbreviation;
                
                        public DaylightSavingsTimeZone(String zoneOffset, String zoneAbbreviation){
                            this.zoneOffset = zoneOffset;
                            this.zoneAbbreviation = zoneAbbreviation;
                        }
                    }
                }
        
                public static final class GeoLocation {
                    public final GeogLocation geogLocation;
                    public final LocalSiteXY localSiteXY[];

                    public GeoLocation(GeogLocation geogLocation, LocalSiteXY[] localSiteXY){
                        this.geogLocation = geogLocation;
                        this.localSiteXY = localSiteXY;
                    }
            
                    public static final class GeogLocation {
                        public final String srs;
                        public final double latitude;
                        public final double longitude;
                
                        public GeogLocation(String srs, double latitude, double longitude){
                            this.srs = srs;
                            this.latitude = latitude;
                            this.longitude = longitude;
                        }
                    }
            
                    public static final class LocalSiteXY {
                
                        public LocalSiteXY(){
                        }
                    }
                }
        
                public static final class Note {
            
                    public Note(){
                    }
                }
        
                public static final class SiteType {
            
                    public SiteType(){
                    }
                }
        
                public static final class SiteProperty {
                    public final String value;
                    public final String name;
            
                    public SiteProperty(String value, String name){
                        this.value = value;
                        this.name = name;
                    }
                }
            }
    
            public static final class Variable {
                public final VariableCode variableCode[];
                public final String variableName;
                public final String variableDescription;
                public final String valueType;
                public final Unit unit;
                public final Options options;
                public final Note note[];
                public final long noDataValue;
                public final VariableProperty variableProperty[];
                public final String oid;
        
                public Variable(VariableCode[] variableCode, String variableName, String variableDescription, String valueType, Unit unit, Options options, Note[] note, long noDataValue, VariableProperty[] variableProperty, String oid){
                    this.variableCode = variableCode;
                    this.variableName = variableName;
                    this.variableDescription = variableDescription;
                    this.valueType = valueType;
                    this.unit = unit;
                    this.options = options;
                    this.note = note;
                    this.noDataValue = noDataValue;
                    this.variableProperty = variableProperty;
                    this.oid = oid;
                }
        
                public static final class VariableCode {
                    public final String value;
                    public final String network;
                    public final String vocabulary;
                    public final long variableID;
                    // public final boolean default;
                    
                    public VariableCode(String value, String network, String vocabulary, long variableID){
                        this.value = value;
                        this.network = network;
                        this.vocabulary = vocabulary;
                        this.variableID = variableID;
                        // this.default = default;
                    }
                }
        
                public static final class Unit {
                    public final String unitCode;
            
                    public Unit(String unitCode){
                        this.unitCode = unitCode;
                    }
                }
        
                public static final class Options {
                    public final Option option[];
            
                    public Options(Option[] option){
                        this.option = option;
                    }
            
                    public static final class Option {
                        public final String name;
                        public final String optionCode;
                
                        public Option(String name, String optionCode){
                            this.name = name;
                            this.optionCode = optionCode;
                        }
                    }
                }
        
                public static final class Note {
                    public Note(){
                    }
                }
        
                public static final class VariableProperty {
                    public VariableProperty(){
                    }
                }
            }
    
            public static final class Values {
                public final ObservationValue value[];
                public final Qualifier qualifier[];
                public final QualityControlLevel qualityControlLevel[];
                public final Method method[];
                public final Source source[];
                public final Offset offset[];
                public final Sample sample[];
                public final CensorCode censorCode[];
        
                public Values(ObservationValue[] value, Qualifier[] qualifier, QualityControlLevel[] qualityControlLevel, Method[] method, Source[] source, Offset[] offset, Sample[] sample, CensorCode[] censorCode){
                    this.value = value;
                    this.qualifier = qualifier;
                    this.qualityControlLevel = qualityControlLevel;
                    this.method = method;
                    this.source = source;
                    this.offset = offset;
                    this.sample = sample;
                    this.censorCode = censorCode;
                }
        
                public static final class ObservationValue {
                    public final String value;
                    public final String[] qualifiers;
                    public final String dateTime;
            
                    public ObservationValue(String value, String[] qualifiers, String dateTime){
                        this.value = value;
                        this.qualifiers = qualifiers;
                        this.dateTime = dateTime;
                    }
                }
        
                public static final class Qualifier {
                    public final String qualifierCode;
                    public final String qualifierDescription;
                    public final long qualifierID;
                    public final String network;
                    public final String vocabulary;
            
                    public Qualifier(String qualifierCode, String qualifierDescription, long qualifierID, String network, String vocabulary){
                        this.qualifierCode = qualifierCode;
                        this.qualifierDescription = qualifierDescription;
                        this.qualifierID = qualifierID;
                        this.network = network;
                        this.vocabulary = vocabulary;
                    }
                }
        
                public static final class QualityControlLevel {
            
                    public QualityControlLevel(){
                    }
                }
        
                public static final class Method {
                    public final String methodDescription;
                    public final long methodID;
            
                    public Method(String methodDescription, long methodID){
                        this.methodDescription = methodDescription;
                        this.methodID = methodID;
                    }
                }
        
                public static final class Source {

                    public Source(){
                    }
                }
        
                public static final class Offset {

                    public Offset(){
                    }
                }
        
                public static final class Sample {

                    public Sample(){
                    }
                }
        
                public static final class CensorCode {

                    public CensorCode(){
                    }
                }
            }
        }
    }
}