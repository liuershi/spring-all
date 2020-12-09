package cn.infocore.springboot.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/9 14:03
 * @Description
 */
public class TestGenerator {
    //生成文件所在项目路径
    private static String baseProjectPath = "D:\\Program Files (x86)\\WXWork\\zhangwei\\study-project\\spring-all\\springboot-mybatis-plus";
    //基本包名
    private static String basePackage = "cn.infocore.springboot";
    //作者
    private static String authorName = "wei.zhang@infocore.cn";
    //要生成的表名, 为空时默认指定库的所有表
    private static String[] tables = {};
    //table前缀
    private static String prefix = "tb_";

    //数据库配置四要素
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://192.168.13.165/springboot_mybatis_plus?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static String username = "root";
    private static String password = "Infocore`1q";

    public static void main(String[] args) {

        AutoGenerator gen = new AutoGenerator();

        // 数据库配置
        gen.setDataSource(new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName(driverName)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password));

        // 全局配置
        gen.setGlobalConfig(new GlobalConfig()
                .setOutputDir(baseProjectPath + "/src/main/java/cn/infocore/springboot/generate")//输出目录
                .setFileOverride(true)// 是否覆盖文件
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columnList
                .setOpen(false)//生成后打开文件夹
                .setAuthor(authorName)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
        );

        // 策略配置
        gen.setStrategy(new StrategyConfig()
                .setTablePrefix(prefix)// 表前缀
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                .setInclude(tables) // 需要生成的表
                .setRestControllerStyle(true)
                .setEntityLombokModel(true)
        );

        // 包配置
        gen.setPackageInfo(new PackageConfig()
                .setParent(basePackage)
                .setController("controller")
                .setEntity("entity")
                .setMapper("dao")
                .setService("service")
                .setServiceImpl("service.impl")
                .setXml("mapper")
        );

        // 执行生成
        gen.execute();
    }
}
