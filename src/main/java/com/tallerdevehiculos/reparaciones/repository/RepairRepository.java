package com.tallerdevehiculos.reparaciones.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.tallerdevehiculos.reparaciones.config.FirebaseConfig;
import com.tallerdevehiculos.reparaciones.entity.Repair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class RepairRepository {

    private static FirebaseConfig firebase= new FirebaseConfig();
    private static Firestore db=firebase.getInstance();
    public static RepairRepository repository= null;

    public static RepairRepository getInstance() {
        if (repository == null){
            repository = new RepairRepository();
        }
        return repository;
    }
    public Repair addRepair  (Repair repair){
        DocumentReference docRef = db.collection("repairs").document();
        repair.setrId(docRef.getId());
//asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(repair);
// result.get() blocks on response
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return repair;
    }

    public  Repair  findRepairById (String rId){

        DocumentReference docRef = db.collection("repairs").document(rId);
// asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
// ...
// future.get() blocks on response
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Repair repair = null;
        if (document.exists()) {
            // convert document to POJO
            repair = document.toObject(Repair.class);
            return repair;
        }
        return repair;
    }

    public List<Repair> findAllVehicles() {

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection("repairs").get();
// future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<Repair> lista = new ArrayList<Repair>();

        for (DocumentSnapshot document : documents)
        {
            lista.add(document.toObject(Repair.class));
        }
        return lista;
    }

    public List<Repair> findRepairByPlate (String licensePlateVehicle){

        ApiFuture<QuerySnapshot> future =
                db.collection("repairs").whereEqualTo("licensePlateVehicle", licensePlateVehicle).get();
// future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<Repair> lista = new ArrayList<Repair>();

        for (DocumentSnapshot document : documents)
        {
            lista.add(document.toObject(Repair.class));
        }
        return lista;
    }

    public  String deleteRepair  (Repair  repair ){

        ApiFuture<WriteResult> writeResult = db.collection("repairs").document(repair.getrId()).delete();
// ...
        try {
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();

            return "{" +
                    "\"status\"" +": \"0\""+
                    "}";

        } catch (ExecutionException e) {
            e.printStackTrace();

            return "{" +
                    "\"status\"" +": \"0\""+
                    "}";
        }

        return "{" +
                "\"status\""+":\"1\""+
                "}";
    }

    public  String editVehicle (Repair  repair ){

        DocumentReference docRef = db.collection("repairs").document(repair.getrId());

        Map<String, Object> updates = new HashMap<>();
        updates.put("repairDate", repair.getRepairDate());
        updates.put("repairBy", repair.getRepairBy());
        updates.put("state", repair.getState());
        updates.put("listStates", repair.getListStates());
        updates.put("spareParts", repair.getSpareParts());
        updates.put("cost", repair.getCost());
        updates.put("partsCost", repair.getPartsCost());
        updates.put("inCharge", repair.getInCharge());


// (async) Update one field
        ApiFuture<WriteResult> future = docRef.update(updates);

// ...
        WriteResult result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();

            return "{" +
                    "\"status\"" +": \"0\""+
                    "}";

        } catch (ExecutionException e) {
            e.printStackTrace();

            return "{" +
                    "\"status\"" +": \"0\""+
                    "}";
        }

        return "{" +
                "\"status\""+":\"1\""+
                "}";
    }
}
