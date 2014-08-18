/**
 * 
 */
package net.martenscs.osgi.mybatis.blueprint;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.type.TypeHandler;
import org.osgi.framework.*;

/**
 * @author cmartens
 * 
 */
public class SqlSessionFactoryBean implements SqlSessionFactory {

	private static final Log LOGGER = LogFactory
			.getLog(SqlSessionFactoryBean.class);

	private DataSource dataSource;

	private TransactionFactory transactionFactory;

	private Properties configurationProperties;

	private String environment = SqlSessionFactoryBean.class.getSimpleName();

	private boolean failFast;

	private List<Interceptor> plugins;

	private List<TypeHandler<?>> typeHandlers;

	private String typeHandlersPackage;

	private Class<?>[] typeAliases;

	private String typeAliasesPackage;

	private Class<?> typeAliasesSuperType;

	private DatabaseIdProvider databaseIdProvider;

	private ObjectFactory objectFactory;

	private ObjectWrapperFactory objectWrapperFactory;

	private SqlSessionFactory delegate;

	private Set<String> mappers;

	public SqlSessionFactoryBean() {
		super();
	}

	private Bundle sourceBundle;

	private SqlSessionFactory getDelegate() {
		if (delegate == null) {
			Configuration configuration = new Configuration(
					new Environment.Builder("default").dataSource(dataSource)
							.transactionFactory(new JdbcTransactionFactory())
							.build());
			if (configurationProperties != null)
				configuration.setVariables(configurationProperties);

			for (String mapper : mappers) {
				try {
					Class<?> clazz = (Class<?>) sourceBundle.loadClass(mapper);
					LOGGER.debug("Adding Mapper:" + mapper);
					configuration.addMapper(clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			if (this.objectFactory != null) {
				configuration.setObjectFactory(this.objectFactory);
			}
			if (this.objectWrapperFactory != null) {
				configuration
						.setObjectWrapperFactory(this.objectWrapperFactory);
			}
			if (this.objectWrapperFactory != null) {
				configuration
						.setObjectWrapperFactory(this.objectWrapperFactory);
			}
			for (TypeHandler<?> typeHandler : getTypeHandlers()) {
				configuration.getTypeHandlerRegistry().register(typeHandler);
			}
			for (Interceptor plugin : this.plugins) {
				configuration.addInterceptor(plugin);
			}
			delegate = new SqlSessionFactoryBuilder().build(configuration);
		}

		return delegate;
	}

	@Override
	public Configuration getConfiguration() {
		return getDelegate().getConfiguration();
	}

	@Override
	public SqlSession openSession() {
		return getDelegate().openSession();
	}

	@Override
	public SqlSession openSession(boolean arg0) {
		return getDelegate().openSession(arg0);
	}

	@Override
	public SqlSession openSession(Connection arg0) {
		return getDelegate().openSession(arg0);
	}

	@Override
	public SqlSession openSession(TransactionIsolationLevel arg0) {
		return getDelegate().openSession(arg0);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0) {
		return getDelegate().openSession(arg0);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0, boolean arg1) {
		return getDelegate().openSession(arg0, arg1);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0,
			TransactionIsolationLevel arg1) {
		return getDelegate().openSession(arg0, arg1);
	}

	@Override
	public SqlSession openSession(ExecutorType arg0, Connection arg1) {
		return getDelegate().openSession(arg0, arg1);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Properties getConfigurationProperties() {
		return configurationProperties;
	}

	public void setConfigurationProperties(Properties configurationProperties) {
		this.configurationProperties = configurationProperties;
	}

	public ObjectFactory getObjectFactory() {
		return objectFactory;
	}

	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}

	public ObjectWrapperFactory getObjectWrapperFactory() {
		return objectWrapperFactory;
	}

	public void setObjectWrapperFactory(
			ObjectWrapperFactory objectWrapperFactory) {
		this.objectWrapperFactory = objectWrapperFactory;
	}

	public Set<String> getMappers() {
		return mappers;
	}

	public void setMappers(Set<String> mappers) {
		this.mappers = mappers;
	}

	public Bundle getSourceBundle() {
		return sourceBundle;
	}

	public void setSourceBundle(Bundle sourceBundle) {
		this.sourceBundle = sourceBundle;
	}

	public TransactionFactory getTransactionFactory() {
		return transactionFactory;
	}

	public void setTransactionFactory(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}

	public List<TypeHandler<?>> getTypeHandlers() {
		return typeHandlers;
	}

	public void setTypeHandlers(List<TypeHandler<?>> typeHandlers) {
		this.typeHandlers = typeHandlers;
	}

	public String getTypeHandlersPackage() {
		return typeHandlersPackage;
	}

	public void setTypeHandlersPackage(String typeHandlersPackage) {
		this.typeHandlersPackage = typeHandlersPackage;
	}

	public Class<?>[] getTypeAliases() {
		return typeAliases;
	}

	public void setTypeAliases(Class<?>[] typeAliases) {
		this.typeAliases = typeAliases;
	}

	public String getTypeAliasesPackage() {
		return typeAliasesPackage;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	public Class<?> getTypeAliasesSuperType() {
		return typeAliasesSuperType;
	}

	public void setTypeAliasesSuperType(Class<?> typeAliasesSuperType) {
		this.typeAliasesSuperType = typeAliasesSuperType;
	}

	public DatabaseIdProvider getDatabaseIdProvider() {
		return databaseIdProvider;
	}

	public void setDatabaseIdProvider(DatabaseIdProvider databaseIdProvider) {
		this.databaseIdProvider = databaseIdProvider;
	}

	public List<Interceptor> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<Interceptor> plugins) {
		this.plugins = plugins;
	}

	public boolean isFailFast() {
		return failFast;
	}

	public void setFailFast(boolean failFast) {
		this.failFast = failFast;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
}
