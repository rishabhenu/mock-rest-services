import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import javax.ws.rs.client.*;

public class dummyMain {

	public static void main(String...strings) {

		
//		try {
//			Class.forName("org.glassfish.jersey.client.JerseyClientBuilder");
//		}catch(ClassNotFoundException e) {
//			System.out.println("class not found");
//			return;
//		}
//		String url = "https://886m4.mocklab.io/rms/auth";
		System.out.println("start");
		String url = "http://localhost:5555/ds2k7/rest/dataservice/shubham/SAJWA1C77G8V92533";

		Client client = ClientBuilder.newClient();
//		Target target = ResteasyClientBuilder.newClient().target(url);
		
		System.out.println("end");
	
	}
	
}
