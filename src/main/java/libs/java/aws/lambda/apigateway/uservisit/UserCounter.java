package libs.java.aws.lambda.apigateway.uservisit;

public interface UserCounter {

	public int incrementAndReturnCount (String user) throws Exception;
}
