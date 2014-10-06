package net.martenscs.osgi.mybatis.blueprint.example.objfactory;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.osgi.framework.Bundle;

public class ExampleObjectFactory extends DefaultObjectFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 249295101544606855L;

	private Bundle sourceBundle;

	private Map<String, Map<Object, Object>> objectFactoryProps = new Hashtable<String, Map<Object, Object>>();

	public ExampleObjectFactory() {
		super();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object create(Class type) {
		return super.create(type);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object create(Class type, List constructorArgTypes,
			List constructorArgs) {
		return super.create(type, constructorArgTypes, constructorArgs);
	}

	public Bundle getSourceBundle() {
		return sourceBundle;
	}

	public void setSourceBundle(Bundle sourceBundle) {
		this.sourceBundle = sourceBundle;
	}

	public Map<String, Map<Object, Object>> getObjectFactoryProps() {
		return objectFactoryProps;
	}

	public void setObjectFactoryProps(
			Map<String, Map<Object, Object>> objectFactoryProps) {
		this.objectFactoryProps = objectFactoryProps;
	}

}