package cn.infocore.springboot.test;


import cn.infocore.springboot.convert.SimpleSourceDestinationMapper;
import cn.infocore.springboot.description.SimpleDestination;
import cn.infocore.springboot.source.SimpleSource;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 15:01
 * @Description
 */
public class SimpleSourceDestinationTest {
    public static void main(String[] args) {
        testSourceToDestination();
        testDestinationToSource();
    }

    private static void testDestinationToSource() {
        SimpleDestination destination = new SimpleDestination("李四", "提莫队长", 26);
        SimpleSource source = SimpleSourceDestinationMapper.INSTANCE.destinationToSource(destination);

        System.out.println(source);
    }

    private static void testSourceToDestination() {
        SimpleSource source = new SimpleSource("张三", "法外狂徒", 25);
        SimpleSourceDestinationMapper mapper = SimpleSourceDestinationMapper.INSTANCE;
        SimpleDestination destination = mapper.sourceToDestination(source);
        System.out.println(destination.toString());

    }
}
