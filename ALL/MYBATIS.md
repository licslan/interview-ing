### Mybatis3+ 学习与总结
#### 以前只知道怎么去使用 其实也不是完全怎么使用了吧 就是编程的时候知道怎么CRUD?
#### LICSLAN回答
     怎么说呢 其实就是一个CRUD工程师 很SB 的吧 但是一直不想就这么平庸 这么平庸下去
     今天就来看看mybatis做了什么吧 我也是硬着头皮去看的 我们一起相互学习 最近在找工作
     也想找到一份满意的工作 既然没有gf 那就在其他方面写点什么吧 学习英语 学习代码源码...
     总之就是不让自己闲着吧  哪怕一个人也无所谓  一个人行走的快  利于思考  不容考虑其他人
     等其他人... so 也 喜欢一个人  暂时而已。
     好的 言归正传~~~~ ^_^
     Mybatis是支持定制化SQL、存储过程和高级映射的持久层框架。主要完成两件事：
     
     A.封装JDBC的操作
     B.利用反射(反射可以了解之前写的内容)完成Java类和SQL之间的转换
     
     
     mybatis的主要目的就是管理执行SQL是参数的输入和输出，编写SQL和结果集的映射是mybatis的主要优点
     mybatis中主要类和接口
     Configuration：将mybatis配置文件中的信息保存到该类中
     SqlSessionFactory：解析Configuration类中的配置信息，获取SqlSession
     SqlSession：负责和数据库交互，完成增删改查
     Executor：mybatis的调度核心，负责SQL的生成
     StatementHandler：封装了JDBC的statement操作
     ParameterHandler：负责完成JavaType到jdbcType的转换
     ResultSetHandler：负责完成结果集到Java Bean的转换
     MappedStatement：代表一个select|update|insert|delete元素
     SqlSource：根据传入的ParamterObject生成SQL
     BoundSql：包含SQL和参数信息
     
     学习Mybatis 主要看上面的A,B 2项怎么实现
![MYBATIS05](https://github.com/licslan/interview-ing/raw/master/ALL-THING/MYBATIS/MYBATIS05.jpg)<br>
![MYBATIS06](https://github.com/licslan/interview-ing/raw/master/ALL-THING/MYBATIS/MYBATIS06.jpg)<br>
![MYBATIS07](https://github.com/licslan/interview-ing/raw/master/ALL-THING/MYBATIS/MYBATIS07.jpg)<br>     
     
     首先来看看SqlSessionFactory和SqlSession源码
     SqlSessionFactory的创建是mybatis的第一步，SqlSession完成数据库增删改查。
     我们先来看看二者的创建
![MYBATIS00](https://github.com/licslan/interview-ing/raw/master/ALL-THING/MYBATIS/MYBATIS00.jpg)
     
     首先创建SqlSessionFactoryBudiler对象，在调用builder方法读取mybatis配置文件，并创建SqlSessionFactory：

![MYBATIS01](https://github.com/licslan/interview-ing/raw/master/ALL-THING/MYBATIS/MYBATIS01.jpg)<br>
![MYBATIS02](https://github.com/licslan/interview-ing/raw/master/ALL-THING/MYBATIS/MYBATIS02.jpg)
    
     不管是调用SqlSessionFactoryBuilder哪个build重载方法，最后调用的都是上面的两种，
     这两种的区别只是采用不同的流读取配置文件(Reader是字符流  InputStream是字节流)，
     最后都会调用build(Configuration config)创建SqlSessionFactory接口的实现类对象
     
     
     当创建SqlSessionFactory完成后下一步就是创建SqlSession：
![MYBATIS03](https://github.com/licslan/interview-ing/raw/master/ALL-THING/MYBATIS/MYBATIS03.jpg) 
 
     接下来我们看看SqlSession的源码：
     
     public class DefaultSqlSession implements SqlSession {
     
      private final Configuration configuration;
      private final Executor executor;
     
      private final boolean autoCommit;
     
      @Override
      public <E> List<E> selectList(String statement, Object parameter, 
      RowBounds rowBounds) {
        try {
          MappedStatement ms = configuration.getMappedStatement(statement);
          return executor.query(ms, wrapCollection(parameter), rowBounds, 
          Executor.NO_RESULT_HANDLER);
        } catch (Exception e) {
          throw ExceptionFactory.wrapException("Error querying database.  
          Cause: " + e, e);
        } finally {
          ErrorContext.instance().reset();
        }
      }
      
      @Override
      public void select(String statement, Object parameter, RowBounds rowBounds, 
      ResultHandler handler) {
        try {
          MappedStatement ms = configuration.getMappedStatement(statement);
          executor.query(ms, wrapCollection(parameter), rowBounds, handler);
        } catch (Exception e) {
          throw ExceptionFactory.wrapException("Error querying database.  
          Cause: " + e, e);
        } finally {
          ErrorContext.instance().reset();
        }
      }
      
      @Override
      public int update(String statement, Object parameter) {
        try {
          dirty = true;
          MappedStatement ms = configuration.getMappedStatement(statement);
          return executor.update(ms, wrapCollection(parameter));
        } catch (Exception e) {
          throw ExceptionFactory.wrapException("Error updating database.  
          Cause: " + e, e);
        } finally {
          ErrorContext.instance().reset();
        }
      }
    }
    
    SqlSession的所有增删改查操作，最后都会落到上面三个方法，而最后的SQL执行都是使用Executor执行。
    
    接下来我们在看看Executor中的query()和update方法，Executor接口有一个抽象实现类BaseExecutor，
    我们调用的query()和update()方法实际属于该类，而该类的query()和update()最后都落到三个子类
    SimpleExecutor、ReuseExecutor、BatchExecutor中。这里我们选SimpleExecutor看看
    public class SimpleExecutor extends BaseExecutor {
     
      @Override
      public int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
        Statement stmt = null;
        try {
          Configuration configuration = ms.getConfiguration();
          StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, 
          RowBounds.DEFAULT, null, null);
          stmt = prepareStatement(handler, ms.getStatementLog());
          //把锅甩给StatementHandler
    	  return handler.update(stmt);
        } finally {
          closeStatement(stmt);
        }
      }
     
      @Override
      public <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, 
      ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        Statement stmt = null;
        try {
          Configuration configuration = ms.getConfiguration();
          StatementHandler handler = configuration.newStatementHandler(wrapper, ms, 
          parameter, rowBounds, resultHandler, boundSql);
          stmt = prepareStatement(handler, ms.getStatementLog());
          //StatementHandler来处理下一步
    	  return handler.<E>query(stmt, resultHandler);
        } finally {
          closeStatement(stmt);
        }
      }
    }
    
    上面prepareStatement()方法内部调用StatementHandler的prepare()方法，
    这一般是我们定制插件的拦截方法。
    
    可以发现最后都交给StatementHandler处理。StatementHandler有三个实现类：
    PreparedStatementHandler、CallableStatementHandler、
    RoutingStatementHandler，我们选择PreparedStatementHandler看一下：
    
    public class PreparedStatementHandler extends BaseStatementHandler {
     
      @Override
      public int update(Statement statement) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        int rows = ps.getUpdateCount();
        Object parameterObject = boundSql.getParameterObject();
        KeyGenerator keyGenerator = mappedStatement.getKeyGenerator();
        keyGenerator.processAfter(executor, mappedStatement, ps, parameterObject);
        return rows;
      }
     
      @Override
      public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        return resultSetHandler.<E> handleResultSets(ps);
      }
     
    }
    
    OK，这里就是我们熟悉的JDBC操作了。
    
    MapperProxy
    到了这了大家可能还有一个疑问，我调用的是DAO接口中的方法，和上面这些好像没关系。
    别急接下来我们就来看看二者是怎么联系起来的
    在mybatis和Spring集合使用中，使用DAO时我们一般使用@Autowired注入，
    但是大家有没有一个疑问，DAO是一个接口，接口是不能创建对象的，这个是怎么完成的呢？
    
    Mybatis获取如何获取Mapper？
    
    SqlSessionFactory sessionFactory = null;  
    String resource = "mybatis-conf.xml";  
     
    sessionFactory = new SqlSessionFactoryBuilder().build(Resources  
                  .getResourceAsReader(resource));
     
    SqlSession sqlSession = sessionFactory.openSession();
    SqlSession session = sqlSessionFactory.openSession();
    AliceMapper mapper = session.getMapper(AliceMapper.class);
    System.out.println("jdk proxy mapper : "+mapper);
    AlicePO alicePo = mapper.getAliceByUsername("licslan");
    如何获取SqlSession上面我们已经了解了，这里重点关注session.getMapper(AliceMapper.class)这一句
![MYBATIS04](https://github.com/licslan/interview-ing/raw/master/ALL-THING/MYBATIS/MYBATIS04.jpg)    
  
    上图描述了getMapper()这个方法的过程，我们来看MapperRegistry中的这个方法：
    public class MapperRegistry {
     
      private final Configuration config;
      private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<Class<?>, 
      MapperProxyFactory<?>>();
     
      public MapperRegistry(Configuration config) {
        this.config = config;
      }
     
      @SuppressWarnings("unchecked")
      public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) 
        knownMappers.get(type);
        if (mapperProxyFactory == null) {
          throw new BindingException("Type " + type + " is not known to the 
          MapperRegistry.");
        }
        try {
          return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
          throw new BindingException("Error getting mapper instance. Cause: " + e, e);
        }
      }
    }
    
    konwMapper会在创建MapperRegistry对象是初始化，key是我们定义的Dao的class对象，value是根据各
    Dao接口的class对象创建的MapperProxyFactory、接下来我们看MapperProxyFactory
    
    
    public class MapperProxyFactory<T> {
      //我们的Dao接口的class对象
      private final Class<T> mapperInterface;
     
      @SuppressWarnings("unchecked")
      protected T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), 
        new Class[] { mapperInterface }, mapperProxy);
      }
     
      public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, 
        mapperInterface, methodCache);
        return newInstance(mapperProxy);
      }
     
    }
    
    到了这一步我们明白了，这是通过动态代理创建Dao接口的动态类的对象，而对接口所有方法的调用，最后都会
    回到调用mapperProxy的invoke方法上(这就是JDK动态代理)。我们去看看mapperProxy对象的invoke方法：
    
    public class MapperProxy<T> implements InvocationHandler, Serializable {
     
      private static final long serialVersionUID = -6424540398559729838L;
      private final SqlSession sqlSession;
      private final Class<T> mapperInterface;
      private final Map<Method, MapperMethod> methodCache;
     
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
    	  //判断你调用的是否是已实现的方法
          if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
          } else if (isDefaultMethod(method)) {
            return invokeDefaultMethod(proxy, method, args);
          }
        } catch (Throwable t) {
          throw ExceptionUtil.unwrapThrowable(t);
        }
        final MapperMethod mapperMethod = cachedMapperMethod(method);
        return mapperMethod.execute(sqlSession, args);
      }
     
    }
    
    if判断我们调用的方法是否是对象中的，我们调用的都是接口的方法，所以直接走
    mapperMethod.execute()。mapperMethod标识我们调用接口中的那个方法
    public class MapperMethod {
     
      private final SqlCommand command;
      private final MethodSignature method;
     
      public MapperMethod(Class<?> mapperInterface, Method method, Configuration config) {
        this.command = new SqlCommand(config, mapperInterface, method);
        this.method = new MethodSignature(config, mapperInterface, method);
      }
     
      public Object execute(SqlSession sqlSession, Object[] args) {
        Object result;
        switch (command.getType()) {
          case INSERT: {
          Object param = method.convertArgsToSqlCommandParam(args);
            result = rowCountResult(sqlSession.insert(command.getName(), param));
            break;
          }
          case UPDATE: {
            Object param = method.convertArgsToSqlCommandParam(args);
            result = rowCountResult(sqlSession.update(command.getName(), param));
            break;
          }
          case DELETE: {
            Object param = method.convertArgsToSqlCommandParam(args);
            result = rowCountResult(sqlSession.delete(command.getName(), param));
            break;
          }
          case SELECT:
            if (method.returnsVoid() && method.hasResultHandler()) {
              executeWithResultHandler(sqlSession, args);
              result = null;
            } else if (method.returnsMany()) {
              result = executeForMany(sqlSession, args);
            } else if (method.returnsMap()) {
              result = executeForMap(sqlSession, args);
            } else if (method.returnsCursor()) {
              result = executeForCursor(sqlSession, args);
            } else {
              Object param = method.convertArgsToSqlCommandParam(args);
              result = sqlSession.selectOne(command.getName(), param);
            }
            break;
          case FLUSH:
            result = sqlSession.flushStatements();
            break;
          default:
            throw new BindingException("Unknown execution method for: " + command.getName());
        }
        if (result == null && method.getReturnType().isPrimitive() && !method.returnsVoid()) {
          throw new BindingException("Mapper method '" + command.getName() 
              + " attempted to return null from a method with a primitive return type 
              (" + method.getReturnType() + ").");
        }
        return result;
      }
    }
    
    是的，这里就和上面的SqlSession接上了，最后这些操作都会归为SqlSession中的update、selectList、
    select操作了。而这其实我们已经知道了，最后SqlSession其实是交给Statement执行SQL命令了。
    
    mybatis-spring如何获取Mapper?
    下面来解决另外一个疑惑@Autowired注入的是个什么鬼？
    
    注入的其实就是动态代理创建的接口实现类型的对象，不过mybatis-spring把sqlSession.getMapper()
    这个动作交个了MapperFactoryBean。而这个实在Spring扫描dao层的时候，为每一个接口分别创建一个
    MapperFactoryBean：
    public class MapperFactoryBean<T> extends SqlSessionDaoSupport implements FactoryBean<T> {
     
      private Class<T> mapperInterface;
     
      public MapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
      }
     
      @Override
      public T getObject() throws Exception {
        return getSqlSession().getMapper(this.mapperInterface);
      }
    mapperInterface就是dao的class对象，因为实现了FactoryBean接口，因此通过@Autowired获取对象时，
    实际是调用getObject方法获取对象，而这里有回到了sqlSession.getMapper()。到此终于和上面接上了
    
    
#### 本文参考 [mybatis](https://blog.csdn.net/heyrian/article/details/81558109)    