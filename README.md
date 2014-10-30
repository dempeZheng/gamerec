Game Rec
=======
 
profile
-----------------------------------
 <br/>
Common Analysis 统计
---------------------

##### countEvent protocol:
    {
        type : 1,
        key : "$appkey:appkeyValue,field:fieldValue,plat:platValue",
        date : "[yyyyMM,yyyyMMdd,yyyy]"
    }
##### uniqueEvent protocol:
    {
        type : 2,
        id : "uniqueID",
        key : "$appkey:appkeyValue,field:fieldValue,plat:platValue",
        date : "[yyyyMM,yyyyMMdd,yyyy]"
    }
##### rankEvent protocol:
    {
          type : 3,
          rank : "version or model",
          key : "$appkey:appkeyValue,field:fieldValue,plat:platValue",
          date : "[yyyyMM,yyyyMMdd,yyyy]"
    }
##### uniqueRankEvent protocol:
    {
        type : 4,
        rank : "version",
        id : "uniqueID",
        key : "$appkey:appkeyValue,field:fieldValue,plat:platValue",
        date : "[yyyyMM,yyyyMMdd,yyyy]"
    }

###### 注释:
  type: count=>1, unique=>2, rank=>3, uniqueRank=>4
Api 接口
-------------
Recommend 推荐系统
-------------
####userCF离线结果<br/>
####itemCF离线结果<br/>
####spark streaming实时推荐<br/>
