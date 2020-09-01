package com.gok.ticketingbatch.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, "oAuth2Authentication");
            createCache(cm, com.gok.ticketingbatch.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.gok.ticketingbatch.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.gok.ticketingbatch.domain.User.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Authority.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.User.class.getName() + ".authorities");
            createCache(cm, com.gok.ticketingbatch.domain.MedicalPractitioner.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.MedicalPractitioner.class.getName() + ".practitionerSchedules");
            createCache(cm, com.gok.ticketingbatch.domain.PractitionerSchedule.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Person.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Person.class.getName() + ".documents");
            createCache(cm, com.gok.ticketingbatch.domain.Person.class.getName() + ".mobiles");
            createCache(cm, com.gok.ticketingbatch.domain.Person.class.getName() + ".addresses");
            createCache(cm, com.gok.ticketingbatch.domain.Person.class.getName() + ".relativeContacts");
            createCache(cm, com.gok.ticketingbatch.domain.Mobile.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.RelativeContact.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.RelativeContact.class.getName() + ".people");
            createCache(cm, com.gok.ticketingbatch.domain.Document.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Address.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.KsrsacAddress.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Patient.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Patient.class.getName() + ".medicalHistories");
            createCache(cm, com.gok.ticketingbatch.domain.Patient.class.getName() + ".patientActivities");
            createCache(cm, com.gok.ticketingbatch.domain.Patient.class.getName() + ".reportedSymptoms");
            createCache(cm, com.gok.ticketingbatch.domain.ReportedSymptom.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.MedicalHistory.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.PatientActivity.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.PatientActivity.class.getName() + ".ambulanceActivities");
            createCache(cm, com.gok.ticketingbatch.domain.AmbulanceActivity.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Prescription.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Ticket.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Ticket.class.getName() + ".ticketActions");
            createCache(cm, com.gok.ticketingbatch.domain.Ticket.class.getName() + ".prescriptions");
            createCache(cm, com.gok.ticketingbatch.domain.Ticket.class.getName() + ".diagnoses");
            createCache(cm, com.gok.ticketingbatch.domain.Ticket.class.getName() + ".tags");
            createCache(cm, com.gok.ticketingbatch.domain.Ticket.class.getName() + ".ticketStatuses");
            createCache(cm, com.gok.ticketingbatch.domain.TicketAction.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.TicketAction.class.getName() + ".assignments");
            createCache(cm, com.gok.ticketingbatch.domain.Assignment.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.TicketStatus.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Diagnosis.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.Tags.class.getName());
            createCache(cm, com.gok.ticketingbatch.domain.EntityAuditEvent.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
