package basisFx;

import basisFx.dataSource.Db;

import java.sql.Connection;
import java.sql.SQLException;

public class DbSchema {

    public DbSchema() {

        String equipment = "CREATE TABLE IF NOT EXISTS Equipment ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,  "
                + "	name VARCHAR(40)"
                + ")";
        String counterparty= "CREATE TABLE IF NOT EXISTS Counterparty ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,  "
                + "	name VARCHAR(40),  "
                + " currencyId INTEGER,  "
                + " FOREIGN KEY (currencyId) REFERENCES Currency(id) "
                + ")";
        String currency= "CREATE TABLE IF NOT EXISTS Currency( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + "	name VARCHAR(40)"
                + ")";
        String employer= "CREATE TABLE IF NOT EXISTS Employer( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " isFired Boolean,   "
                + "	name VARCHAR(40) "
                + ")";
        String ratePerHourHistory= "CREATE TABLE IF NOT EXISTS EMPLOYEESRATEPERHOUR ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " employerId INTEGER,   "
                + " rate DOUBLE,   "
                + " startDate Date,   "
                + " FOREIGN KEY (employerId) REFERENCES Employer(id) on delete cascade , "
                + " UNIQUE  (employerId, startDate)"
                + ")";
        String timeRecordingForEmployers= "CREATE TABLE IF NOT EXISTS TimeRecordingForEmployers ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " employerId INTEGER,   "
                + " date Date,   "
                + " hours DOUBLE,  "
                + " FOREIGN KEY (employerId) REFERENCES Employer(id) on delete cascade , "
                + " UNIQUE  (employerId, date)"
                + ")";
        String exchangeRates= "CREATE TABLE IF NOT EXISTS ExchangeRates ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " currencyId INTEGER,   "
                + " rate DOUBLE,   "
                + " startDate Date,   "
                + " FOREIGN KEY (currencyId) REFERENCES Currency(id)  on delete cascade , "
                + " UNIQUE  (currencyId, startDate)"
                + ")";
        String product= "CREATE TABLE IF NOT EXISTS Product( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + "	name VARCHAR(40), "
                + "	havingSleeve Boolean "
                + ")";
        String productPriceStore= "CREATE TABLE IF NOT EXISTS ProductPrice ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " productId INTEGER,   "
                + " price DOUBLE,   "
                + " startDate Date,   "
                + " FOREIGN KEY (productId) REFERENCES Product(id)  on delete cascade , "
                + " UNIQUE  (productId, startDate)"
                + ")";
        String packetSize = "CREATE TABLE IF NOT EXISTS PacketSize( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + "	size VARCHAR(40) "
                + ")";
        String packet = "CREATE TABLE IF NOT EXISTS Packet( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + "	packetSizeId INTEGER, "
                + "	counterpartyId INTEGER, "
                + " FOREIGN KEY (packetSizeId) REFERENCES PacketSize(id), "
                + " FOREIGN KEY (counterpartyId) REFERENCES Counterparty(id) "
                + ")";
        String packetPriceStore= "CREATE TABLE IF NOT EXISTS PacketPrice ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " packetId INTEGER,   "
                + " price DOUBLE,      "
                + " startDate Date,   "
                + " FOREIGN KEY (packetId) REFERENCES Packet(id)  on delete cascade , "
                + " UNIQUE  (packetId, startDate)"
                + ")";
        String packetProductAccordance= "CREATE TABLE IF NOT EXISTS PacketProductAccordance ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " packetSizeId INTEGER,   "
                + " productId INTEGER,   "
                + " number INTEGER,   "
                + " FOREIGN KEY (productId) REFERENCES Product(id), "
                + " FOREIGN KEY (packetSizeId) REFERENCES PacketSize(id) "
                + ")";
        String label = "CREATE TABLE IF NOT EXISTS Label( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + "	name VARCHAR(40), "
                + "	counterpartyId INTEGER, "
                + " FOREIGN KEY (counterpartyId) REFERENCES Counterparty(id) "
                + ")";
        String labelPriceStore= "CREATE TABLE IF NOT EXISTS LabelPrice ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, "
                + " labelId INTEGER,   "
                + " price DOUBLE,   "
                + " startDate Date, "
                + " FOREIGN KEY (labelId) REFERENCES Label(id)  on delete cascade , "
                + " UNIQUE  (labelId, startDate)"
                + ")";
         String sleevePrice= "CREATE TABLE IF NOT EXISTS SleevePrice ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " price DOUBLE,   "
                + " startDate Date UNIQUE "
                + "    "
                + ")";
        String paper= "CREATE TABLE IF NOT EXISTS Paper ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + "	counterpartyId INTEGER, "
                + " FOREIGN KEY (counterpartyId) REFERENCES Counterparty(id) "
                + ")";
        String paperPriceStore= "CREATE TABLE IF NOT EXISTS PaperPrice ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " paperId INTEGER,   "
                + " price DOUBLE,   "
                + " startDate Date,   "
                + " FOREIGN KEY (paperId) REFERENCES Paper(id)  on delete cascade , "
                + " UNIQUE  (paperId, startDate)"
                + ")";

        String jumboAccounting= "CREATE TABLE IF NOT EXISTS JumboAccounting ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " overallWeight Double,   "
                + " CounterpartyId INTEGER,   "
                + " date Date,  "
                + " FOREIGN KEY (CounterpartyId) REFERENCES Counterparty(id)  on delete cascade "
                + ")";
        String Jumbo= "CREATE TABLE IF NOT EXISTS Jumbo ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " width INTEGER,   "
                + " numberOfProduct INTEGER    "
                + ")";

        String outputPerDay= "CREATE TABLE IF NOT EXISTS outputPerDay ( "
                + "	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,   "
                + " EquipmentId INTEGER,   "
                + " productId INTEGER,     "
                + " rodsNumber INTEGER,     "
                + " jumboId INTEGER,       "
                + " packetId INTEGER,       "
                + " paperCounterpartyId INTEGER,   "
                + " date Date,  "
                + " FOREIGN KEY (EquipmentId) REFERENCES Equipment(id)  on delete cascade, "
                + " FOREIGN KEY (productId) REFERENCES product(id)  on delete cascade, "
                + " FOREIGN KEY (packetId) REFERENCES packet(id)  on delete cascade, "
                + " FOREIGN KEY (jumboId) REFERENCES jumbo(id)  on delete cascade, "
                + " FOREIGN KEY (paperCounterpartyId) REFERENCES Counterparty(id)  on delete cascade "
                + ")";



        String viewFired= "Create  view Fired  as " +
                " SELECT  * from  employer  where employer.ISFIRED=true ORDER BY  employer.ID " ;

        String viewActualRate= "Create  view ActualEmployersRate  as " +
                " SELECT  r.employerId as id,r.employerId as employerId , r.rate, r.startDate, e.name, e.isFired"+
                " from  employer as e,"+
                " (select * from EMPLOYEESRATEPERHOUR where (employerId, startDate)"+
                " in (select employerId, max(startDate) from  EMPLOYEESRATEPERHOUR group by employerId)) as r"+

                " where r.employerId=e.id and e.isFired = false"+
//                " where r.employerId=e.id  "+
                " ORDER BY r.employerId";
        String viewTimeRecordingAndSalary= "Create  view TimeRecordingAndSalary  as " +
                "SELECT EMPLOYERID, DATE, HOURS , rate, rate*HOURS AS salary, e.ISFIRED,e.NAME FROM(\n" +
                "\t\tSELECT t.EMPLOYERID, t.DATE,t.HOURS,\n" +
                "\t\t(select RATE from EMPLOYEESRATEPERHOUR where (employerId, startdate)\n" +
                "\t\tin (\n" +
                "\t\t\tSELECT employerId, startdate FROM (\n" +
                "\t\t\t\tselect employerId,max(startdate) AS startdate\n" +
                "\t\t\t\tfrom ( SELECT * \n" +
                "\t\t\t\t\tfrom EMPLOYEESRATEPERHOUR WHERE startdate <= t.DATE\n" +
                "\t\t\t\t)\n" +
                "\t\t\t\tgroup by employerId\n" +
                "\t\t\t) AS RATEDADE \n" +
                "\t\t\tWHERE RATEDADE.employerId=t.EMPLOYERID\n" +
                "\t\t) ) AS rate \n" +
                "\t\tFROM TimeRecordingForEmployers t\n" +
                "\t)AS timerec \n" +
                "\tleft JOIN   EMPLOYER e ON timerec.EMPLOYERID=e.id";
//        String viewSalaryByMonth= "Create  view SalaryByMonth  as " +
//                "SELECT * FROM (\n" +
//                "SELECT SUM(SALARY) AS SALARY, EMPLOYERID,  MONTH(\"DATE\") AS sMonth, YEAR(\"DATE\") AS sYear\n" +
//                "  FROM TIMERECORDINGANDSALARY\n" +
//                " GROUP BY MONTH(DATE),YEAR(\"DATE\"), EMPLOYERID) AS salary\n" +
//                " LEFT JOIN Employer ON Employer.id=salary.EMPLOYERID";
//        String viewSalaryByYear=  "Create  view SalaryByYear  as "
//                +"SELECT * FROM (\n" +
//                "SELECT * FROM (\n" +
//                " SELECT SUM(SALARY) AS SALARY, EMPLOYERID,  YEAR(DATE) AS YSALARY\n" +
//                "                  FROM TIMERECORDINGANDSALARY\n" +
//                "                 GROUP BY YEAR(DATE), EMPLOYERID) AS SALARY\n" +
//                "                LEFT JOIN EMPLOYER ON EMPLOYER.ID=SALARY.EMPLOYERID)";
//        String viewTotalSalaryByYear=  "Create  view TotalSalaryByYear  as "
//                +"\n" +
//                " SELECT SUM(SALARY) AS SALARY  ,ySalary  \n" +
//                "                  FROM SalaryByYear\n" +
//                "                 GROUP BY ySalary";
//        String viewTotalSalaryByMonth=  "Create  view TotalSalaryByMonth  as "
//                +"\n" +
//                "  SELECT SUM(SALARY) AS SALARY  , SMONTH   \n" +
//                "        FROM SalaryByMonth   \n" +
//                "         GROUP BY SMONTH";

        String viewproductResult=  "Create  view productResult  as "
                +"SELECT rodsnumber,PRODUCTID,jumboid,OutDate,outid,PACKETID,EQUIPMENTID,numberofproductForRod,price,\n" +
                "rodsnumber*numberofproductForRod AS productAmount,\n" +
                "rodsnumber*numberofproductForRod*price AS totalCost\n" +
                "from \n" +
                "( SELECT \n" +
                "o.rodsnumber, o.PRODUCTID, o.jumboid , o.date AS OutDate, o.ID AS outid ,\n" +
                "o.PACKETID, o.EQUIPMENTID, j.numberofproduct AS numberofproductForRod,\n" +
                "(select PRICE from PRODUCTPRICE where (PRODUCTID, startDate)\n" +
                "in (\n" +
                "select PRODUCTID, max(startDate) from PRODUCTPRICE\n" +
                "WHERE startDate<=o.DATE\n" +
                "and PRODUCTID=o.PRODUCTID\n" +
                "group by PRODUCTID \n" +
                ")) AS price \n" +
                "FROM\n" +
                "OUTPUTPERDAY o, jumbo j \n" +
                "WHERE o.jumboid= j.ID\n" +
                ")";
//        String viewproductResultByMonth=  "Create  view productResultByMonth  as "
//                +"SELECT * FROM (\n" +
//                "SELECT * FROM (\n" +
//                "SELECT SUM(PRODUCTAMOUNT) AS products,SUM(TOTALCOST) AS cost, PRODUCTID,  MONTH( OUTDATE) AS  pMonth, YEAR(OUTDATE) AS pYear\n" +
//                "  FROM PRODUCTRESULT\n" +
//                " GROUP BY YEAR(OUTDATE),MONTH(OUTDATE), PRODUCTID) AS result1\n" +
//                " )LEFT JOIN product ON PRODUCTID=product.id\n" +
//                "  ";
//        String viewproductResultByYEAR=  "Create  view productResultByYEAR  as "
//                +"SELECT * FROM (\n" +
//                "SELECT * FROM (\n" +
//                "SELECT SUM(PRODUCTAMOUNT) AS products,SUM(TOTALCOST) AS cost, PRODUCTID,   YEAR(OUTDATE) AS pYear\n" +
//                "  FROM PRODUCTRESULT\n" +
//                " GROUP BY YEAR(OUTDATE), PRODUCTID) AS result1\n" +
//                " )LEFT JOIN product ON PRODUCTID=product.id\n" +
//                "  ";


        String packetResultFull=  "Create  view packetResultFull  as "
                +"SELECT  \n" +
                "\tOUTDATE,PACKETID,PRODUCTAMOUNT,PRODUCTID,PACKETprice, p.PACKETSIZEID,ACCORDANCE.NUMBER AS numberinpacket,\n" +
                "\tPRODUCTAMOUNT/ACCORDANCE.NUMBER AS totalpacketamount, \n" +
                "\tPRODUCTAMOUNT/ACCORDANCE.NUMBER * PACKETprice AS totalpacketcost\n" +
                "\tfrom\n" +
                "\t(SELECT   \n" +
                "\t\tproductamount.OUTDATE,\n" +
                "\t\tproductamount.PACKETID,\n" +
                "\t\tproductamount.PRODUCTAMOUNT,\n" +
                "\t\tproductamount.PRODUCTID, \n" +
                "\t\t(select PRICE from PACKETPrice where (PACKETID, startDate)\n" +
                "\t\t\tin (\n" +
                "\t\t\tselect PACKETID, max(startDate) from PACKETPrice\n" +
                "\t\t\tWHERE startDate<=productamount.OUTDATE\n" +
                "\t\t\tand PACKETID=productamount.PACKETID\n" +
                "\t\t\tgroup by PACKETID \n" +
                "\t\t\t)) AS PACKETprice \n" +
                "\t\t\tfrom \n" +
                "\t\t\tproductResult AS productamount\n" +
                "\t\t\t)\n" +
                "\tAS subquery,PACKET AS p, PACKETPRODUCTACCORDANCE AS ACCORDANCE\n" +
                "\tWHERE\n" +
                "\tp.ID=SUBQUERY.PACKETID \n" +
                "\tand ACCORDANCE.PACKETSIZEID=p.PACKETSIZEID \n" +
                "\tand ACCORDANCE.PRODUCTID=SUBQUERY.PRODUCTID";
//
//        String packetResMonthFull=  "Create  view packetResMonthFull  as "
//                +"\n" +
//                "SELECT cost,amount,name AS supplier,SIZE, pMonth,pYear FROM (\n" +
//                "SELECT * FROM (\n" +
//                "SELECT * FROM (\n" +
//                "SELECT SUM(TOTALPACKETCOST) AS cost,SUM(TOTALPACKETAMOUNT) AS amount, PACKETID,  MONTH( OUTDATE) AS  pMonth, YEAR(OUTDATE) AS pYear\n" +
//                "  FROM packetResultFull\n" +
//                " GROUP BY YEAR(OUTDATE),MONTH(OUTDATE), PACKETID) AS res\n" +
//                " LEFT JOIN PACKET ON PACKET.ID=res.PACKETID\n" +
//                " )AS res2 \n" +
//                " LEFT JOIN PACKETSIZE ON PACKETSIZE.ID=res2.packetsizeid\n" +
//                " LEFT JOIN COUNTERPARTY ON COUNTERPARTY.ID=res2.COUNTERPARTYid\n" +
//                " )\n" +
//                "   ";
//        String packetResYearFull=  "Create  view packetResYearFull  as "
//                +"\n" +
//                "SELECT cost,amount,name AS supplier,SIZE, pYear FROM (\n" +
//                "SELECT * FROM (\n" +
//                "SELECT * FROM (\n" +
//                "SELECT SUM(TOTALPACKETCOST) AS cost,SUM(TOTALPACKETAMOUNT) AS amount, PACKETID, YEAR(OUTDATE) AS pYear\n" +
//                "  FROM packetResultFull\n" +
//                " GROUP BY YEAR(OUTDATE), PACKETID) AS res\n" +
//                " LEFT JOIN PACKET ON PACKET.ID=res.PACKETID\n" +
//                " )AS res2 \n" +
//                " LEFT JOIN PACKETSIZE ON PACKETSIZE.ID=res2.packetsizeid\n" +
//                " LEFT JOIN COUNTERPARTY ON COUNTERPARTY.ID=res2.COUNTERPARTYid\n" +
//                " )\n" +
//                "   ";
//        String packetResTotalMonth=  "Create  view packetResTotalMonth  as "
//                +"SELECT sum(cost) AS cost,sum(amount) AS amount ,pMonth,pYear FROM (\n" +
//                "SELECT * FROM (\n" +
//                "SELECT * FROM (\n" +
//                "SELECT SUM(TOTALPACKETCOST) AS cost,SUM(TOTALPACKETAMOUNT) AS amount, PACKETID,  MONTH( OUTDATE) AS  pMonth, YEAR(OUTDATE) AS pYear\n" +
//                "  FROM packetResultFull\n" +
//                " GROUP BY YEAR(OUTDATE),MONTH(OUTDATE), PACKETID\n" +
//                " ) AS res\n" +
//                " LEFT JOIN PACKET ON PACKET.ID=res.PACKETID\n" +
//                " )AS res2 \n" +
//                " LEFT JOIN PACKETSIZE ON PACKETSIZE.ID=res2.packetsizeid\n" +
//                " LEFT JOIN COUNTERPARTY ON COUNTERPARTY.ID=res2.COUNTERPARTYid\n" +
//                " )\n" +
//                " GROUP BY pMonth,pYear\n" +
//                "   ";
//        String packetResTotalYear=  "Create  view packetResTotalYear  as "
//                +"SELECT sum(cost) AS cost,sum(amount) AS amount,pYear FROM (\n" +
//                "SELECT * FROM (\n" +
//                "SELECT * FROM (\n" +
//                "SELECT SUM(TOTALPACKETCOST) AS cost,SUM(TOTALPACKETAMOUNT) AS amount, PACKETID,  MONTH( OUTDATE) AS  pMonth, YEAR(OUTDATE) AS pYear\n" +
//                "  FROM packetResultFull\n" +
//                " GROUP BY YEAR(OUTDATE),MONTH(OUTDATE), PACKETID\n" +
//                " ) AS res\n" +
//                " LEFT JOIN PACKET ON PACKET.ID=res.PACKETID\n" +
//                " )AS res2 \n" +
//                " LEFT JOIN PACKETSIZE ON PACKETSIZE.ID=res2.packetsizeid\n" +
//                " LEFT JOIN COUNTERPARTY ON COUNTERPARTY.ID=res2.COUNTERPARTYid\n" +
//                " )\n" +
//                " GROUP BY pYear\n" +
//                "   ";


        create(
                equipment,
                currency,
                counterparty,
                employer,
                ratePerHourHistory,
                timeRecordingForEmployers,
                exchangeRates,
                product,
                productPriceStore,
                packetSize,
                packet,
                packetPriceStore,
                packetProductAccordance,
                label,
                labelPriceStore,
                sleevePrice,
                paper,
                paperPriceStore,
                Jumbo,
                outputPerDay,
                jumboAccounting,
                jumboAccounting
//                ,
//
//                viewFired,
//                viewTimeRecordingAndSalary,

//                viewSalaryByMonth,
//                viewSalaryByYear,
//                viewActualRate,
//                viewTotalSalaryByYear,
//                viewTotalSalaryByMonth,

//                viewproductResult,
//
//                packetResultFull,


//                packetResMonthFull,
//                packetResYearFull,
//                packetResTotalMonth,
//                packetResTotalYear

        );

    }
    
    private void create(String ...val){
    
        for (String tableName : val) {
          try {
              Connection connection = Db.connection;
              connection.createStatement().execute(tableName);
//
            } catch (SQLException e) {
                System.out.println("Не создалась таблица");
                System.err.println(tableName.toUpperCase());
                System.err.println(e);


            }
        }
        
    }
    

        
      
   
        
 
}
//SELECT * FROM SLEEVEPRICE WHERE YEAR(STARTDATE) =YEAR(CURRENT_DATE);
//SELECT * FROM SLEEVEPRICE WHERE YEAR(STARTDATE) =2016
//SELECT * FROM SLEEVEPRICE WHERE STARTDATE > (NOW() - INTERVAL 1 DAY) AND STARTDATE <= NOW() ;
//SELECT * FROM SLEEVEPRICE   ORDER BY STARTDATE DESC LIMIT 2   ;
//SELECT * FROM SLEEVEPRICE WHERE STARTDATE >= '2019-01-11' AND STARTDATE <= '2019-01-15' ;