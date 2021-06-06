package com.dropwizard;

import com.dropwizard.api.AdminRestAPI;
import com.dropwizard.api.ClientRestAPI;
import com.dropwizard.api.CustomerRestAPI;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CustomerApplication extends Application<CustomerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CustomerApplication().run(args);
    }

    @Override
    public String getName() {
        return "Customer";
    }

    @Override
    public void initialize(final Bootstrap<CustomerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CustomerConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(ClientRestAPI.class);
        environment.jersey().register(CustomerRestAPI.class);
        environment.jersey().register(AdminRestAPI.class);
    }

}
