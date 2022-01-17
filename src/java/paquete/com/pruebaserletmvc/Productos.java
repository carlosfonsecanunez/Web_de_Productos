/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete.com.pruebaserletmvc;

import java.util.Date;

/**
 *
 * @author kai
 */
public class Productos {

    

    
    
    public Productos(String coArt,String noArt,String catArt,String desArt,double preArt,Date fecArt){
        this.coArt=coArt;
        this.noArt=noArt;
        this.catArt=catArt;
        this.desArt=desArt;
        this.preArt=preArt;
        this.fecArt=fecArt;
        
    }
    public Productos(String noArt,String catArt,String desArt,double preArt,Date fecArt){
        this.noArt=noArt;
        this.desArt=desArt;
        this.preArt=preArt;
        this.catArt=catArt;
        this.preArt=preArt;
        this.fecArt=fecArt;
    }
    
    private String coArt;
    private String noArt;
    private String catArt;
    private String desArt;
    private double preArt;
    private Date fecArt;
    /**
     * @return the coArt
     */
    public String getCoArt() {
        return coArt;
    }

    /**
     * @param coArt the coArt to set
     */
    public void setCoArt(String coArt) {
        this.coArt = coArt;
    }

    /**
     * @return the noArt
     */
    public String getNoArt() {
        return noArt;
    }

    /**
     * @param noArt the noArt to set
     */
    public void setNoArt(String noArt) {
        this.noArt = noArt;
    }
    
    /**
     * @return the catArt
     */
    public String getCatArt() {
        return catArt;
    }

    /**
     * @param catArt the catArt to set
     */
    public void setCatArt(String catArt) {
        this.catArt = catArt;
    }

    /**
     * @return the desArt
     */
    public String getDesArt() {
        return desArt;
    }

    /**
     * @param desArt the desArt to set
     */
    public void setDesArt(String desArt) {
        this.desArt = desArt;
    }

    /**
     * @return the preArt
     */
    public double getPreArt() {
        return preArt;
    }

    /**
     * @param preArt the preArt to set
     */
    public void setPreArt(double preArt) {
        this.preArt = preArt;
    }
    
    /**
     * @return the fecArt
     */
    public Date getFecArt() {
        return fecArt;
    }

    /**
     * @param fecArt the fecArt to set
     */
    public void setFecArt(Date fecArt) {
        this.fecArt = fecArt;
    }
    
    @Override
    public String toString(){
        return "codigo: "+coArt+" nombre: "+noArt+" categoria: "+catArt+" descripcion: "+desArt+" precio: "+preArt+" fecha: "+fecArt;
    }
    
}
