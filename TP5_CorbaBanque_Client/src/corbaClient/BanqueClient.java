package corbaClient;

import corbaBanque.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class BanqueClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            IBanqueRemote banque = IBanqueRemoteHelper.narrow(ncRef.resolve_str("Banque"));

            Compte c = new Compte(1, 1000);
            banque.creerCompte(c);
            banque.verser(1, 200);
            banque.retirer(1, 50);
            Compte c1 = new Compte(2, 500);
            banque.creerCompte(c1);
            banque.verser(2, 300);
            System.out.println("Solde du compte 1 : " + banque.getCompte(1).solde);
            System.out.println("Solde du compte 2 : " + banque.getCompte(2).solde);
            double convertedAmount = banque.conversion(100);
            System.out.println("Montant converti : " + convertedAmount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
