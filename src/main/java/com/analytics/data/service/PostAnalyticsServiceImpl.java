package com.analytics.data.service;

import com.analytics.data.dto.CarPostDTO;
import com.analytics.data.entity.BrandAnalyticsEntity;
import com.analytics.data.entity.CarModelAnalyticsEntity;
import com.analytics.data.entity.PriceAnalyticsEntity;
import com.analytics.data.repository.BrandAnalyticsRepository;
import com.analytics.data.repository.CarModelAnalyticsRepository;
import com.analytics.data.repository.PriceAnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostAnalyticsServiceImpl implements PostAnalyticsService {

    @Autowired
    private BrandAnalyticsRepository brandAnalyticsRepository;

    @Autowired
    private CarModelAnalyticsRepository carModelAnalyticsRepository;

    @Autowired
    private PriceAnalyticsRepository priceAnalyticsRepository;


    @Override
    public void saveDataAnalytics(CarPostDTO carPostDTO) {
        saveBrandAnalytics(carPostDTO.getBrand());
        saveCarModelAnalytics(carPostDTO.getModel());
        savePriceAnalytics(carPostDTO.getModel(), carPostDTO.getPrice());
    }

    private void saveBrandAnalytics(String brand) {
        BrandAnalyticsEntity brandAnalyticsEntity = new BrandAnalyticsEntity();


        brandAnalyticsRepository.findByBrand(brand).ifPresentOrElse(item -> {
            item.setPosts(item.getPosts() + 1);
            brandAnalyticsRepository.save(item);
        }, () -> {
            brandAnalyticsEntity.setBrand(brand);
            brandAnalyticsEntity.setPosts(1L);
            brandAnalyticsRepository.save(brandAnalyticsEntity);
        });
    }

    private void saveCarModelAnalytics(String model) {
        CarModelAnalyticsEntity carModelAnalyticsEntity = new CarModelAnalyticsEntity();

        carModelAnalyticsRepository.findByModel(model).ifPresentOrElse(item -> {
            item.setPosts(item.getPosts() + 1);
            carModelAnalyticsRepository.save(item);
        }, () -> {
            carModelAnalyticsEntity.setModel(model);
            carModelAnalyticsEntity.setPosts(1L);
            carModelAnalyticsRepository.save(carModelAnalyticsEntity);
        });
    }

    private void savePriceAnalytics(String carModel, Double price) {
        PriceAnalyticsEntity priceAnalyticsEntity = new PriceAnalyticsEntity();

        priceAnalyticsEntity.setPrice(price);

        priceAnalyticsEntity.setModel(carModel);
        priceAnalyticsRepository.save(priceAnalyticsEntity);
    }
}
