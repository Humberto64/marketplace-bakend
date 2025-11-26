package com.troyecto.marketplace.controllers;

import com.troyecto.marketplace.repositories.ProductRepository;
import com.troyecto.marketplace.repositories.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // ✅ Permitir acceso desde el frontend (localhost:3000)

public class DashboardController {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    @GetMapping("/stats")
    public ResponseEntity<?> getDashboardStats() {
        try{
            long products= productRepository.count();
            long stores= storeRepository.count();
            List<Object[]> deptStats= productRepository.countProductsByStore();
            List<Map<String,Object>> productsPerStore=new ArrayList<>();
            for(Object[] row: deptStats){
                productsPerStore.add(Map.of(
                        "name",row[0],//Nombre de la tienda
                        "value",((Number) row[1]).intValue()
                ));
            }
            Map<String,Object> response=new HashMap<>();
            response.put("products",products);
            response.put("stores",stores);
            response.put("productsPerStore",productsPerStore);
            // Simular datos de tendencias (podrás reemplazar por fechas reales)
            List<Map<String, Object>> trendData = List.of(
                    Map.of("month", "Jan", "newEmployees", 5, "resignations", 2),
                    Map.of("month", "Feb", "newEmployees", 3, "resignations", 1),
                    Map.of("month", "Mar", "newEmployees", 6, "resignations", 3),
                    Map.of("month", "Apr", "newEmployees", 4, "resignations", 2),
                    Map.of("month", "May", "newEmployees", 8, "resignations", 1)
            );
            response.put("trendData",trendData);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error loadin dashboard stats:"+ e.getMessage()));
        }
    }

}
