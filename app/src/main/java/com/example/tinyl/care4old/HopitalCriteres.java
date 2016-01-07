package com.example.tinyl.care4old;

/**
 * Created by tinyl on 07/01/16.
 */
public class HopitalCriteres {

    private int id;
    private String dateEntree;
    private String dateSortie;
    private String raison;

    public HopitalCriteres(){}

    public HopitalCriteres(String dateEntree, String dateSortie, String raison){
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.raison = raison;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(String dateEntree) {
        this.dateEntree = dateEntree;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String toString(){
        return "ID : "+id+"\nDate Entrée : "+dateEntree+"\nDate Sortie : "+dateSortie+"\nRaison : "+raison;
    }
}
