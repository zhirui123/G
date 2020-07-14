package com.huagongwuliu.waybillelectronic.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MyBatisConfig {

    /**
     * 将组建添加到容器中
     *
     * @return
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                /**
                 * 设置驼峰命名规则
                 * 即数据库的字段，如 aBc 中间要是存在"_" 时，则会自动转换匹配
                 */
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
