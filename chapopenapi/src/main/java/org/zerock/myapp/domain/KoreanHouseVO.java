package org.zerock.myapp.domain;

import lombok.Value;


@Value
public class KoreanHouseVO {
	
   private String brandnm;
   private Integer brandno;
   private String goodsnm;
   private Integer goodsno;
   private Integer price;
   
   private String selltype;
   private Double posx;
   private Double posy;

} // end class
