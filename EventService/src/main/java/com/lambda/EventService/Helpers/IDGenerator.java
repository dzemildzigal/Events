package com.lambda.EventService.Helpers;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;;
import java.io.Serializable;
import java.util.Properties;

public class IDGenerator extends SequenceStyleGenerator {

    public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
    public static final Long VALUE_PREFIX_DEFAULT = 0L;
    private Long valuePrefix;

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final Long NUMBER_FORMAT_DEFAULT = 0L;
    private Long numberFormat;
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
        return id != null ? id : super.generate(session, object);
    }
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        valuePrefix = ConfigurationHelper.getLong(VALUE_PREFIX_PARAMETER, params, Math.toIntExact(VALUE_PREFIX_DEFAULT));
        numberFormat = ConfigurationHelper.getLong(NUMBER_FORMAT_PARAMETER, params, Math.toIntExact(NUMBER_FORMAT_DEFAULT));
    }


}