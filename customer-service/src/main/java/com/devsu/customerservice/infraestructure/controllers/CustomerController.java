package com.devsu.customerservice.infraestructure.controllers;


import com.devsu.customerservice.domain.service.ICustomerService;
import com.devsu.customerservice.infraestructure.controllers.in.CreateCustomer;
import com.devsu.customerservice.infraestructure.controllers.in.CustomerUpdateRequest;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerCreateResponse;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerInfoResponse;
import com.devsu.customerservice.infraestructure.controllers.out.CustomerUpdateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/devsu/test/v1/")
@RequiredArgsConstructor
public class CustomerController implements ICustomerController {

    private final ICustomerService customerService;

    @Override
    @PostMapping("/clientes")
    public ResponseEntity<Mono<CustomerCreateResponse>> createCustomer(@Valid @RequestBody CreateCustomer createCustomer) {
        return new ResponseEntity<>(customerService.createCustomer(createCustomer), HttpStatus.CREATED);
    }


    @Override
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Mono<CustomerInfoResponse>> getCustomer(@PathVariable("id") String id) {
        return new ResponseEntity<>(customerService.genCustomer(id), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/clientes/{identification}")
    public ResponseEntity<Mono<CustomerUpdateResponse>> updateCustomer(@PathVariable String identification, @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return new ResponseEntity<>(customerService.updateCustomer(customerUpdateRequest,identification), HttpStatus.OK);
    }

    @DeleteMapping("/clientes/{identification}")
    @Override
    public ResponseEntity<Mono<Void>> deleteCustomer(@PathVariable String identification) {
        return new ResponseEntity<>(customerService.deleteCustomer(identification), HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping("/clientes")
    public ResponseEntity<Flux<CustomerInfoResponse>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }


}
