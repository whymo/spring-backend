package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Value;


@Value
public class EvChargerVO {
	
   private String addr;
   private Integer chargeTp;
   private Integer cpId;
   private String cpNm;
   private Integer cpStat;
   private Integer cpTp;
   private Integer csId;
   private String csNm;
   private Double lat;
   private Double longi;
   private Date statUpdateDatetime;

} // end class
