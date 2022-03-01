package codegym.vn.service.impl;

import codegym.vn.entity.Contract;
import codegym.vn.entity.Customer;
import codegym.vn.repository.ContractRepository;
import codegym.vn.repository.CustomerRepository;
import codegym.vn.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }
}
