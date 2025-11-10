package BanqueImpl;

import corbaBanque.Compte;
import corbaBanque.IBanqueRemotePOA;

import java.util.HashMap;
import java.util.Map;

public class BanqueImpl extends IBanqueRemotePOA {
    private Map<Integer, Compte> comptes = new HashMap<>();

    @Override
    public void creerCompte(Compte cpte) {
        comptes.put(cpte.code, cpte);
        System.out.println("Compte créé :  avec code " + cpte.code);
    }

    @Override
    public void verser(float mt, int code) {
        Compte cpte = comptes.get(code);
        if (cpte != null) {
            cpte.solde += mt;
            System.out.println("Versement de " + mt + " effectué sur le compte " + code);
        } else {
            System.out.println("Compte introuvable pour le code : " + code);
        }
    }

    @Override
    public void retirer(float mt, int code) {
        Compte cpte = comptes.get(code);
        if (cpte != null) {
            if (cpte.solde >= mt) {
                cpte.solde -= mt;
                System.out.println("Retrait de " + mt + " effectué sur le compte " + code);
            } else {
                System.out.println("Solde insuffisant pour le retrait sur le compte " + code);
            }
        } else {
            System.out.println("Compte introuvable pour le code : " + code);
        }
    }

    @Override
    public Compte getCompte(int code) {
        return comptes.get(code);
    }

    @Override
    public Compte[] getComptes() {
        return comptes.values().toArray(new Compte[0]);
    }

    @Override
    public double conversion(float mt) {
        double taux = 0.91; 
        return mt * taux;
    }
}