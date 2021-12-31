//package com.generator.mp;
//
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//
//public class YunGenerator {
//
//    public static void main(String[] args) {
//
//        // 1、创建代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 2、全局配置
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setOutputDir(System.getProperty("user.dir") + "/generator/src/main/java");
//        globalConfig.setAuthor("王宁"); // TODO 修改作者
//        globalConfig.setOpen(false);
//        globalConfig.setFileOverride(true);
////         globalConfig.setSwagger2(true); // TODO 生成PO关闭 生成VO开启
//
//        mpg.setGlobalConfig(globalConfig);
//
//        // 3、数据源配置
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/db01?autoReconnect=true&autoReconnectForPools=true&useUnicode=true&characterEncoding=UTF8&useSSL=false&allowMultiQueries=true&tinyInt1isBit=false&zeroDateTimeBehavior=convertToNull");
//        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
//        dataSourceConfig.setUsername("root");
//        dataSourceConfig.setPassword("123456");
//
//        mpg.setDataSource(dataSourceConfig);
//
//        // 4、包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(null); //模块名
//        pc.setParent("com.wn.generator");
//        pc.setController("controller");
//        pc.setEntity("entity");
//        pc.setService("service.mybatisplus");
//        pc.setMapper("mapper");
//        mpg.setPackageInfo(pc);
//
//        // 5、策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setInclude("sandboxie"); //对哪一张或多张表生成代码
//        strategy.setNaming(NamingStrategy.underline_to_camel); //数据库表映射到实体的命名策略
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel); //数据库表字段映射到实体的命名策略
//        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
//        strategy.setRestControllerStyle(true); //restful api风格控制器
//        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
//
//        mpg.setStrategy(strategy);
//
//        // 6、执行
//        mpg.execute();
//
//    }
//
//}
