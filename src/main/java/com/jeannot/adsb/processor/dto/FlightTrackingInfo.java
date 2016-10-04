package com.jeannot.adsb.processor.dto;

import java.util.Date;

/**
 * Java object derived from the JSON exposed by
 * http://www.adsbexchange.com/data
 * NB: only some of the fields are used. There are others which I don't know what they do...
 *
 */
public final class FlightTrackingInfo {
	
	//    "Id":3417474
	public final long id;
	//    "Reg":"EC-KDT"
	public final String reg;
	//    "Alt":7500
	public final int alt;
	//    "GAlt":7964
	public final int gAlt;
	//    "InHg":30.3838558
	public final float inHg;
	//    "Lat":51.001419
	public final float lat;
	//    "Long":0.008685
	public final float longit;
	//    "PosTime":1475612470250
	public final Date posTime;
	//    "Spd":259.8
	public final float spd;
	//    "Trak":163.4
	public final float trak;
	//    "Type":"A320"
	public final String type;
	//    "Mdl":"Airbus A320 216"
	public final String mdl;
	//    "Man":"Airbus"
	public final String man;
	//    "Op":"Vueling Airlines"
	public final String op;
	//    "Vsi":3776
	public final int vsi;
	//    "Dst":19.57
	public final float dst;
	//    "Brng":217.4
	public final float brng;
	//    "Engines":"2"
	public final int engines;
	//    "EngType":3
	public final int engType;
	//    "Cou":"Spain"
	public final String cou;
	//    "Year":"2007"	
	public final int year;
	
	public FlightTrackingInfo(long id, String reg, int alt, int gAlt, float inHg,
			float lat, float longit, Date posTime, float spd, float trak,
			String type, String mdl, String man, String op, int vsi, float dst,
			float brng, int engines, int engType, String cou, int year) {
		super();
		this.id = id;
		this.reg = reg;
		this.alt = alt;
		this.gAlt = gAlt;
		this.inHg = inHg;
		this.lat = lat;
		this.longit = longit;
		this.posTime = posTime;
		this.spd = spd;
		this.trak = trak;
		this.type = type;
		this.mdl = mdl;
		this.man = man;
		this.op = op;
		this.vsi = vsi;
		this.dst = dst;
		this.brng = brng;
		this.engines = engines;
		this.engType = engType;
		this.cou = cou;
		this.year = year;
	}

}
