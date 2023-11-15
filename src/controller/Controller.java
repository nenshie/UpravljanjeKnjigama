/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Autor;
import model.Knjiga;
import model.User;
import model.Zanr;

/**
 *
 * @author neven
 */
public class Controller {
    
    private DBBroker dbb;
    
    public List<Knjiga> listaKnjiga  = new ArrayList<>();
    public List<Autor> listaAutora = new ArrayList<>();
    public List<User> listaUsera = new ArrayList<>();

    private static Controller instance;
    public static Controller getInstance(){
        if(instance == null){
             instance = new Controller();
            }
        return instance;
    }
    
    private Controller() {
    
        dbb = new DBBroker();
        
        User u1 = new User(1, "nena", "nena");
        User u2 = new User(2, "duda", "duda");
        User u3 = new User(3, "uki", "uki");
        
        listaUsera.add(u1);
        listaUsera.add(u2);
        listaUsera.add(u3);

        
       /* Autor autor1 = new Autor("Ivo", "Andric", 1892, "Biografija autora Ive Andrica bla bla");
        Autor autor2 = new Autor("Danilo", "Kis", 1935, "Biografija autora Dnaila Kisa bla bla");
        Autor autor3 = new Autor("Mesa", "Selimovic", 1910, "Biografija autora Mese Selimovica bla bla");
        
        Knjiga knjiga1 = new Knjiga("Na drini cuprija", autor1,"1234567890", 1945, Zanr.ROMAN);
        Knjiga knjiga2 = new Knjiga("Basta, pepeo", autor2,"0987654321", 1982, Zanr.DRAMA);
        Knjiga knjiga3 = new Knjiga("Tvrdjava", autor3,"1122334455", 1970, Zanr.ISTORIJSKI);
        
        
        listaKnjiga.add(knjiga1);
        listaKnjiga.add(knjiga2);
        listaKnjiga.add(knjiga3);
        
        listaAutora.add(autor1);
        listaAutora.add(autor2);
        listaAutora.add(autor3);
*/
        
  
    }

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    public List<Autor> getListaAutora() {
        return listaAutora;
    }

    public void setListaAutora(List<Autor> listaAutora) {
        this.listaAutora = listaAutora;
    }

    public void obrisiKnjigu(int id) {
        
        dbb.obrisiKnjigu(id);
        
       // listaKnjiga.remove(selektovaniRed);
    }

    public void dodajKnjigu(Knjiga novaKnjiga) {
       // listaKnjiga.add(novaKnjiga);
        dbb.dodajKnjigu(novaKnjiga);
        
        
    }

    public List<Knjiga> ucitajListuKnjigaIzBaze() {
        this.listaKnjiga = dbb.ucitajListuKnjigaIzBaze();
        return this.listaKnjiga;
    }

    public List<Autor> ucitajListaAutoraIzBaze() {
        return dbb.ucitajListaAutoraIzBaze();
    }

    public void azurirajKnjigu(Knjiga knjigaZaIzmenu) {
        dbb.azurirajKnjigu(knjigaZaIzmenu);
    }

    public List<User> getListaUsera() {
        return listaUsera;
    }

    public boolean login1(String username, String pass) {
        for(User u: listaUsera){
            if(u.getUsername().equals(username) && u.getPassword().equals(pass)){
                 return true;
               }
       }
        return false;
    }

    public boolean login2(String username, String pass) {

        return dbb.login(username, pass);
        
    }

    public List<Knjiga> filtriraj(String autor, String naziv) {
            List<Knjiga> rezultat = new ArrayList<>();
            
           if(autor != null && naziv == null){
            
                for(Knjiga k : listaKnjiga){
                String autorKnjige = k.getAutor().getIme() + " " + k.getAutor().getPrezime();
                    if(autorKnjige.contains(autor)){
                    rezultat.add(k);
                    }
                    
                }
            
            }
            if(naziv != null && autor == null){
            
                for(Knjiga k : listaKnjiga){
                    if(k.getNaslov().contains(naziv)){
                    rezultat.add(k);
                    }
                    
                }
            
            }
            
            if(naziv != null && autor != null){
            
               for(Knjiga k : listaKnjiga){
                String autorKnjige = k.getAutor().getIme() + " " + k.getAutor().getPrezime();
                    if(autorKnjige.contains(autor) && k.getNaslov().contains(naziv)){
                    rezultat.add(k);
                    }

               }
            }
            
            
            
            List<Knjiga> rezultat2 = new ArrayList<>();
            rezultat2 = listaKnjiga.stream()
                    .filter(k->(k.getNaslov().contains(naziv))
                    && (autor != null && (k.getAutor().getIme() + " " + k.getAutor().getPrezime()).contains(autor)))
                    .collect(Collectors.toList());
            
            return rezultat;
    
    }

    public List<Knjiga> filtriraj2(String autor, String naziv) {

        return dbb.filtriraj(autor, naziv);
    }

    
}
