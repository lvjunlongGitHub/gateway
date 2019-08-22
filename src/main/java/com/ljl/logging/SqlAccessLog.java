package com.ljl.logging;

/**
 * <P>
 *     sql访问日志
 * </P>
 * @author lvjunlong
 * @date 2019/8/22 下午7:14
 */
public class SqlAccessLog {

    /**当前访问数据源*/
    private  String dataSource;
    /**当前生成SQL*/
    private  String sql;
    /**调用DAO类名*/
    private  String JpaRepositoryClass;
    /**是否分表*/
    private Boolean isSharding= false;
    /**sql执行时间*/
    private String accessTime;
    public String getDataSource() {
        return dataSource;
    }
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
    public String getSql() {
        return sql;
    }
    public void setSql(String sql) {
        this.sql = sql;
    }
    public String getJpaRepositoryClass() {
        return JpaRepositoryClass;
    }
    public void setJpaRepositoryClass(String jpaRepositoryClass) {
        JpaRepositoryClass = jpaRepositoryClass;
    }

    public String getAccessTime() {
        return accessTime;
    }
    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public Boolean getIsSharding() {
        return isSharding;
    }
    public void setIsSharding(Boolean isSharding) {
        this.isSharding = isSharding;
    }
    @Override
    public String toString() {
        return "dataSource=[" + dataSource + "]`sql=" + sql + "`JpaRepositoryClass=" + JpaRepositoryClass
                + "`accessTime=" + accessTime+"`isSharding=" + isSharding;
    }
}
