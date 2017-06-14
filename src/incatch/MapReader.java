/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incatch;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
 *
 * @author CCIAA
 */
public class MapReader {
    private String mapname, cfgname;
    private Map map;
    private BufferedReader br;
    
    public MapReader(String mn)
    {
        mapname=mn;
        map= new Map();
        
        int index=mapname.lastIndexOf(".");
        if(index<0)
        {
            System.err.println("Errore non trovo la mappa");
            System.exit(-1);
        }
        String temp= mapname.substring(0, index);
        temp+=".cfg";
        cfgname=temp;
        System.out.println("file di configurazione: "+ cfgname);
        readCfg();
        loadMap();
        
  
    }
   
    public boolean readCfg()
    {
        System.out.println("sto per leggere il file "+cfgname);
        FileReader f;
        try  {
            
            f=new FileReader(cfgname);
        }
        
        catch (FileNotFoundException e) {
            System.err.println("non trovo il file " +cfgname);
            return false;
        }
        
        br=new BufferedReader(f);
        
            String riga="";

            while(true) {
            try{
              riga=br.readLine();
              
            }
            
            catch (IOException e){
            e.printStackTrace();
            return false;
            }
            
              if(riga==null)
                break;
              parse(riga);
              
              //long ts= getTimestamp();
              //v.addElement(ts);     
        }
        
        return true;
        
    }
    
    public void parse(String riga)
    {
        int index=riga.indexOf("#");
        String rigavalida;
        if(index>=0) {
            rigavalida=riga.substring(0, index);
        }
        else {
            rigavalida=riga;
        }
        
        if(rigavalida.equals(""))
        {
            
            return;
        }
        
        rigavalida=rigavalida.trim();
        
        System.out.println("rigavalida: "+ rigavalida);
        
        if(rigavalida.equals("[ul]"))
        {
            String northing="";
            
            try{    
                northing=br.readLine();
            }
            catch (IOException e)  {
                e.printStackTrace();
            }
            
            String easting="";
            
            try{
                easting=br.readLine();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            
            System.out.println("northing: " +northing);
            System.out.println("easting: "+easting);
            
            double n=Double.parseDouble(northing);
            double e=Double.parseDouble(easting);
            
            System.out.println("n: " + n);
            System.out.println("e: " + e);
            
            Point2D ul= new Point2D.Double(n, e);
            
            System.out.println("ul: " + ul.toString());
            map.setUl(ul);
            
            //System.out.println("mappa: " + map.toString());
            
        }
        
        if(rigavalida.equals("[br]"))
        {
            
            
            System.out.println("Sono in br");
            String northing="";
            
            try{    
                northing=br.readLine();
            }
            catch (IOException e)  {
                e.printStackTrace();
            }
            
            String easting="";
            
            try{
                easting=br.readLine();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            
            System.out.println("northing: " +northing);
            System.out.println("easting: "+easting);
            
            double n=Double.parseDouble(northing);
            double e=Double.parseDouble(easting);
            
            System.out.println("n: " + n);
            System.out.println("e: " + e);
            
            Point2D btmr= new Point2D.Double(n, e);
            
            System.out.println("btmr: " + btmr.toString());
            map.setBr(btmr);
            
            //System.out.println("mappa: " + map.toString());
            
        }
        
        if(rigavalida.equals("[map]"))
        {
            loadMap();
           
            
            //map.setMapImage()
        }
            
        /**
            //System.out.println("ss " +ss);
            long timestamp=Long.valueOf(ss.trim()).longValue();

            int index2=riga.indexOf("pose");
                if(index2>0)
                {

                    String sp= riga.substring(index2+6);
                    System.out.println("sp: "+sp);
                    String x= sp.substring(sp.indexOf("p")+4, sp.indexOf(","));
                            System.out.println("x: "+x);
                    String temp=sp.substring(sp.indexOf(",")+1);
                    String y= temp.substring(0, temp.indexOf(","));
                    System.out.println("y: "+y);

                    double xd=Double.valueOf(x);
                    System.out.println("xd: "+xd);
                    double yd=Double.valueOf(y);
                    System.out.println("yd: "+yd);
                    Point2D p= new Point2D.Double(xd,yd);

                    String q= temp.substring(temp.indexOf("q")+4, temp.indexOf("z")-3);

                    System.out.println("q: "+q);

                    String z= temp.substring(temp.indexOf("z")+7,temp.indexOf("\"}"));
                    System.out.println("z: "+z);
                }
            }
            */
    }
    
    private void loadMap() {
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(mapname));
         } catch (IOException e) {
         }
        
        map.setMapImage(img);
            int w=map.getMapImage().getWidth();
            System.out.println("w: "+w);
        
    }
    
    public Map getMap() {
        return map;
    }
    
}
