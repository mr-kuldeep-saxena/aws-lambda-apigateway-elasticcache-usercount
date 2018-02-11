package libs.java.aws.lambda.apigateway.uservisit.elasticcache;

import java.net.InetSocketAddress;

import libs.java.aws.lambda.apigateway.uservisit.UserCounter;
import net.spy.memcached.MemcachedClient;

/**
 * Basic implementation using memcached. Uses -
 * AmazonEleasticCacheClusterClient-1.1.1.jar, which can be download from AWS
 * elasticache cluster console, after you create cluster Clustering logic is
 * implemented in client library, to connect to cluster ip and communicate to
 * node to which send request.
 * 
 * Refer to Setup doc for configuring, elasticache cluster, api gateway and
 * lambda
 * 
 * 
 * @author Kuldeep
 *
 */
public class UserCounterImpl implements UserCounter {

	private MemcachedClient client;

	public UserCounterImpl(String ip, int port) throws Exception {
		client = new MemcachedClient(new InetSocketAddress(ip, port));

	}

	public int incrementAndReturnCount(String user) throws Exception {

		Object o = client.get(user);
		int count = 1;
		if (o != null) {
			count = Integer.parseInt(String.valueOf(o)) + 1;
		}
		client.set(user, 0, count);
		return count;
	}

}
