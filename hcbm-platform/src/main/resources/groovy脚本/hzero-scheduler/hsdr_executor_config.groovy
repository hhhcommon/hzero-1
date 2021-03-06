package script.db

databaseChangeLog(logicalFilePath: 'script/db/hsdr_executor_config.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-20-hsdr_executor_config") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hsdr_executor_config_s', startValue:"1")
        }
        createTable(tableName: "hsdr_executor_config", remarks: "执行器配置") {
            column(name: "config_id", type: "bigint(20)", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "executor_id", type: "bigint(20)",  remarks: "执行器ID，hsdr_executor.executor_id")  {constraints(nullable:"false")}  
            column(name: "address", type: "varchar(" + 30 * weight + ")",  remarks: "执行器地址")  {constraints(nullable:"false")}  
            column(name: "max_concurrent", type: "int(11)",  remarks: "最大并发数")   
            column(name: "weight", type: "int(11)",  remarks: "执行器权重")  {constraints(nullable:"false")}  
            column(name: "object_version_number", type: "bigint(20)",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint(20)",   defaultValue:"0",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }

        addUniqueConstraint(columnNames:"executor_id,address",tableName:"hsdr_executor_config",constraintName: "hsdr_executor_config_u1")
    }
}