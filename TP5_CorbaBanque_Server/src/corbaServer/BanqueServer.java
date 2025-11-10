import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import BanqueImpl.BanqueImpl;


public class BanqueServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            BanqueImpl banqueImpl = new BanqueImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(banqueImpl);
            corbaBanque.IBanqueRemote href = corbaBanque.IBanqueRemoteHelper.narrow(ref);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Banque";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);
            System.out.println("Serveur Banque prêt et en attente de requêtes ...");
            orb.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


