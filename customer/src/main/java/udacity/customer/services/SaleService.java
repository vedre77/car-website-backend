package udacity.customer.services;

import org.springframework.stereotype.Service;
import udacity.customer.model.Order;
import udacity.customer.model.Sale;
import udacity.customer.model.SaleStatus;
import udacity.customer.repository.SaleRepository;

import java.util.List;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void markOrderAsSale(Order userOrder) {
        // System.out.println("User order: " + userOrder);
        Sale sale = new Sale();
        sale.setSaleStatus(SaleStatus.PROCESSED);
        sale.setOrder(userOrder);
        saleRepository.save(sale);
        List<Sale> userSales = saleRepository.findAll();
        System.out.println("User sales: " + userSales);

    }
}
