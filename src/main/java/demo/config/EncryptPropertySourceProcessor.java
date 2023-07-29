package demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.*;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class EncryptPropertySourceProcessor implements BeanFactoryPostProcessor, EnvironmentAware, PriorityOrdered {

    private ConfigurableEnvironment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            List<PropertySource<?>> composite = new ArrayList<>();
            MutablePropertySources propertySources = this.environment.getPropertySources();
            Iterator<PropertySource<?>> iterator = propertySources.iterator();
            while (iterator.hasNext()) {
                PropertySource<?> propertySource = iterator.next();
                String name = propertySource.getName();

                if (ObjectUtils.nullSafeEquals(name, "ApolloPropertySources")) {
                    composite.add(new EncryptPropertySource(propertySource.getName(), propertySource));
                }
                if (propertySource instanceof MapPropertySource) {
                    composite.add(new EncryptPropertySource(propertySource.getName(), propertySource));
                }
            }
            // 将包装的PropertySource在Environment中进行替换
            for (PropertySource<?> propertySource : composite) {
                propertySources.replace(propertySource.getName(), propertySource);
            }

        } catch (Exception e) {
            log.error("Exception!", e);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
