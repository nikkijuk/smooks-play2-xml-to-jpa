package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.transform.dom.DOMSource;

import org.milyn.Smooks;
import org.milyn.SmooksException;
import org.milyn.container.ExecutionContext;
import org.milyn.event.report.HtmlReportGenerator;
import org.milyn.payload.JavaResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import play.Logger;
import play.libs.Yaml;
import play.mvc.BodyParser.Of;
import play.mvc.BodyParser.Xml;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.list;

import com.avaje.ebean.Ebean;

import example.model.CustomerOrder;

public class Application extends Controller {

	static {
		try {
			if (Ebean.find(CustomerOrder.class).findRowCount() == 0) {
				// load yaml data
				Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml
						.load("default-orders.yml");
				// Insert employees
				Ebean.save(all.get("orders"));
				Logger.info("Defaults added");

			}
		} catch (Exception e) {
			Logger.error("Defaults couldn't be added "+e.getMessage(), e);
		}
	}

	protected static CustomerOrder runSmooks(Document xml) throws IOException,
			SAXException, SmooksException {

		// Instantiate Smooks with the config...
		Smooks smooks = new Smooks("smooks-config.xml");

		try {
			// Create an exec context - no profiles....
			ExecutionContext executionContext = smooks.createExecutionContext();
			// The result of this transform is a set of Java objects...
			JavaResult result = new JavaResult();

			// Configure the execution context to generate a report...
			executionContext.setEventListener(new HtmlReportGenerator(
					"report.html"));

			// Filter the input message to extract, using the execution
			// context...
			smooks.filterSource(executionContext, new DOMSource(xml), result);

			return (CustomerOrder) result.getBean("order");
		} finally {
			smooks.close();
		}
	}

	@Of(Xml.class)
	public static Result saveXml() {
		Document xml = request().body().asXml();
		CustomerOrder order = null;
		try {
			order = runSmooks(xml);
			order.save();
			return ok("Saved " + order.id);
//		} catch (SmooksException sme) {
//			String msg = "Unmarshalling failed :" + sme.getMessage();
//			//Logger.error(msg, sme);
//			return ok(msg);
//		} catch (SAXException | IOException se) {
//			String msg = "Smooks initialization failed :" + se.getMessage();
//			//Logger.error(msg, se);
//			return ok(msg);
		} catch (Exception e) {
			String msg = "When all other fails :" + e.getMessage();
			//Logger.error(msg, e);
			return ok(msg);
		}
	}

    public static Result list() {
    	List<CustomerOrder> orders = CustomerOrder.find.findList();
    	return ok(list.render(orders));
     }
	
	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

}
