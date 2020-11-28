package com.tallerdevehiculos.reparaciones.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import com.tallerdevehiculos.reparaciones.entity.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepairRepository {
    @Autowired
    private DynamoDBMapper mapper;

    public Repair addRepair  (Repair repair){
        mapper.save(repair);
        return repair;
    }

    public  Repair  findRepairById (String rId){
        return mapper.load(Repair.class,rId);
    }

    public PaginatedScanList<Repair> findAllVehicles() {
        return mapper.scan(Repair.class, new DynamoDBScanExpression());
    }

    public List<Repair> findRepairByPlate (String licensePlateVehicle){

        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":v1", new AttributeValue().withS(licensePlateVehicle));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("licensePlateVehicle = :v1")
                .withExpressionAttributeValues(eav);

        List<Repair> replies =  mapper.scan(Repair.class, scanExpression);

        return replies;
    }

    public  String deleteRepair  (Repair  repair ){
        mapper.delete(repair);
        return "El vehiculo fue eliminado";
    }

    public  String editVehicle (Repair  repair ){
        mapper.save(repair,buildExpression(repair));
        return "Cambios realizados";
    }

    private DynamoDBSaveExpression buildExpression(Repair repair){
        DynamoDBSaveExpression dynamoDBSaveExpression=new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue>expectedMap= new HashMap<>();
        expectedMap.put("rId",new ExpectedAttributeValue(new AttributeValue().withS(repair.getrId())) );
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
