/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echec;

/**
 *
 * @author Axel
 */
public enum Couleur {
    BLANC("Blanc"), NOIR("Noir");
    
    private final String couleur;
    
    private Couleur(String couleur)
    {
        this.couleur = couleur;
    }
    
    @Override
    public String toString()
    {
        return this.couleur;
    }
    
    public static Couleur getCouleurOpposee(Couleur couleur)
    {
        return couleur.equals(Couleur.BLANC) ? Couleur.NOIR : Couleur.BLANC;
    }
}
