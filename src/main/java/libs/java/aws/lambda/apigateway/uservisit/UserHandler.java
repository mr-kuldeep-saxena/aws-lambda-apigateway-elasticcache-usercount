package libs.java.aws.lambda.apigateway.uservisit;

import com.fasterxml.jackson.databind.ObjectMapper;

import libs.java.aws.lambda.apigateway.uservisit.elasticcache.UserCounterImpl;

/**
 * This class shows integration with API gateway to provide dynamic
 * manipulation. It leverages AWS Elastic cache to store user visit count. As
 * you know lambda is stateless container, we need some place to persistent user
 * data. For the purpose we are using AWS elastic cache with memcached
 * implementation.
 * 
 * 
 * @author Kuldeep
 *
 */
public class UserHandler {

	private UserCounter uc;

	public UserHandler() throws Exception {
		String ip = "xxxxxxxxxxx.xxxxxx.cfg.use1.cache.amazonaws.com";
		int port = 11211;

		uc = new UserCounterImpl(ip, port);

	}
	private ObjectMapper objMapper = new ObjectMapper();


	public String visit(String user) throws Exception {

		return objMapper.writeValueAsString("You visited " + uc.incrementAndReturnCount(user) + " times.");
	}

	public static void main(String[] args) throws Exception {

		/*
		 * elastic cache cluster is not available from internet. Though you can
		 * configured NAT to pass request to cluster, however you should avoid
		 * it. To make this program work, please make sure same VPC and subnet
		 * is used by elastic cache cluster and your lambda function.
		 */
	
		new UserHandler().visit("ks");
	}
}
