package com.zzw.spring.boot.base.contorller;

import com.zzw.spring.boot.base.SpringBootBaseApplication;
import com.zzw.spring.boot.base.jmx.SimpleBean;
import com.zzw.spring.boot.base.domain.Person;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.*;

/**
 * @author zhaozhiwei
 * @description
 * @date 2018/9/30 21:25
 */
@Slf4j
@RestController
//@ConditionalOnBean(annotation = {Configurable.class})
public class DemoController {

    @Autowired
    private SimpleBean simpleBean;
    @Resource
    private Person person;
    @Value("${zzw.config}")
    private String config;

    @GetMapping("jmx/simple-bean")
    public SimpleBean simpleBean(@RequestParam(required = false) Long id,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) Integer value) {
        if (id != null) {

            simpleBean.setId(id);
        }
        if (name != null) {
            simpleBean.setName(name);
        }
        if (value != null) {
            simpleBean.setValue(value);
        }
        return simpleBean;
    }

    @GetMapping("config/getPerson")
    public Person getPerson(){
        return person;
    }

    public static void main(String[] args) throws IOException {
//        ClassLoader classLoader = SpringBootBaseApplication.class.getClassLoader();
//
//        Enumeration<URL> resources = classLoader.getResources("META-INF/spring.factories");
//        while (resources.hasMoreElements()) {
//            System.out.println(resources.nextElement());
//        }
//        System.out.println(Integer.toBinaryString((1 << 29) - 1));
//        System.out.println(Integer.toBinaryString(-1 << 29));
//        System.out.println(Integer.toBinaryString((-1 << 29) | 0));
//        System.out.println(Integer.toBinaryString(-1));
        NamedThreadFactory factory = new NamedThreadFactory("mgetByFuture");
        ExecutorService executor = new ThreadPoolExecutor(0, 200, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), factory);

        executor.execute(() -> {
            System.out.println(111);
        });
        executor.shutdown();
    }
}
