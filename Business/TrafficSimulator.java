package Business;

import Business.Entities.Configuration;
import Business.Entities.Reservation;
import Business.Entities.Slot;
import Business.Entities.Vehicle;
import Persistence.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class TrafficSimulator implements Runnable{

    private final SlotDAO slotDAO = new SlotDAO();
    private final SqlConfigurationDao sqlConfigurationDao = new SqlConfigurationDao();
    private final SqlDao sqlDao = new SqlDao(sqlConfigurationDao);
    private final VehicleDao vehicleDao = new VehicleDao();
    private final ReservationDao reservationDao = new ReservationDao();

    private volatile int i;

    public TrafficSimulator() throws FileNotFoundException {
    }

    public void run (){
        //BUCLE INFINITO
        while(i == 1){

            //LEEMOS EK FICHERO EN BUSCA DEL TIEMPO
            try{
                Configuration configuration = sqlConfigurationDao.readJson();
                double timeOfWaitUntilNextActionInSimulation = configuration.getTimeVehicle();
                //ESPERAMOS LA CANTIDAD DE TIEMPO INDICADA
                try{
                    Thread.sleep((long) (timeOfWaitUntilNextActionInSimulation * 1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //NUMERO DE PLAZAS DISPONIBLES
                List<Slot> allSlotsFree = slotDAO.readAllSlotsContentInDb();
                int amountOfSlots = allSlotsFree.size();

                //CON EL NUMERO DE VEHICLES SIMULATED TENEMOS EEL NUMERO DE PLAZAS RESERVADAS POR NO USUARIOS
                List<Vehicle> vehiclesSimulated =  vehicleDao.readSpecificVehicleOfDb("nameOfUserAccount", "SIMULATOR");
                int amountOfReservedButNotUserSlots = vehiclesSimulated.size();

                //CALCULAMOS LAS PROBABILIDAD (PLAZAS  RESERVADAS POR NO USUAARIO DIVIDIDO EL TOTAL D EPLAZAS TANTO LIBRES COMO NO DE USUARIOS)
                float probability = (float) amountOfReservedButNotUserSlots / (amountOfSlots + amountOfReservedButNotUserSlots);

                //GENERAMOS UN NUMERO RANDOM
                Random randomNumbers = new Random();
                int resultOfTheRandomNumber = randomNumbers.nextInt(2);

                //HEMOS SUPERADO LA PROBABILIDAD METEMOS UN COCHE
                if (probability < resultOfTheRandomNumber){
                    //MIRAMOS SI HAY PLAZAS PARA METER COCHES
                    if(!allSlotsFree.isEmpty()){
                        //MIRAMOS LA PRIMERA PLAZA LIBRE Y GENERAMOS UN VEHICULO QUE CUMPLA LAS CONDICIONES
                        Slot slotThatIsGoingToBeSimulated = allSlotsFree.get(0);
                        //CREAMOS UNA MATRICULA ALEATORIA
                        String plateOfTheSimulatedCar =  ("A" + randomNumbers.nextInt(26)).concat(("A" + randomNumbers.nextInt(26))).concat(("A" + randomNumbers.nextInt(26))).concat(String.valueOf(randomNumbers.nextInt(1000)));
                        //MIRAMOS QUE LA MATRICULA NO EXISTA YA (EN CASO DE HACERLO PUES NO CREAMOS EL VEHICULO)
                        Boolean problemInLicensePlate = false;
                        for(Vehicle vehicle : vehiclesSimulated){
                            if(vehicle.getVehicleLicense().equals(plateOfTheSimulatedCar)){
                                problemInLicensePlate = true;
                            }
                        }
                        //NO ESTA REPETIDA LA MATRICULA
                        if(!problemInLicensePlate){
                            //METEMOS EL VEHICULO EN LA BASE DE DATOS
                            vehicleDao.insertNewVehicleInDb(plateOfTheSimulatedCar, slotThatIsGoingToBeSimulated.getTypeOfPlace(), "SIMULATOR");
                            //RESERVAMOS LA PLAZA Y ELIMINAMOS LAS PLAZA LIBRE
                            LocalDateTime dateOfReservation = LocalDateTime.now();
                            reservationDao.insertNewReservationInDb(plateOfTheSimulatedCar, String.valueOf(dateOfReservation), "SIMULATOR", slotThatIsGoingToBeSimulated.getNumber(), slotThatIsGoingToBeSimulated.getFloor(),0,0,1,slotThatIsGoingToBeSimulated.getTypeOfPlace());
                            slotDAO.deleteSpecificSlot(String.valueOf(slotThatIsGoingToBeSimulated.getNumber()));
                    }   }
                }

                //NO HEMOS SUPERADO LA PROBABILIDAD SACAMOS UN COCHE
                if(probability > resultOfTheRandomNumber){
                    //MIRAMOS SI HAY COCHES QUE PODAMOS SACAR
                    if(!vehiclesSimulated.isEmpty()){
                        //MIRAMOS EL PRIMER VEHICULO Y ESE ES EL QUE SACAMOS
                        Vehicle vehicleSimulatedThatIsGoingToBeRemoved = vehiclesSimulated.get(0);
                        //MIRAMOS LA PLAZA QUE OCUPA RESERVADA
                        List<Reservation> vehicleReservation = reservationDao.readSpecificReservationOfDb("licencePlate", vehicleSimulatedThatIsGoingToBeRemoved.getVehicleLicense());
                        Reservation slotThatIsGoingToBeFree = vehicleReservation.get(0);
                        //CREAMOS EL SLOT LIBRE
                        slotDAO.insertNewSlotInDb(slotThatIsGoingToBeFree.getNumber(),slotThatIsGoingToBeFree.getFloor(),0,0,slotThatIsGoingToBeFree.getTypeOfPlace());
                        //AHORA ELIMINAMOS EL VEHICULO Y LA PLAZA RESERVADA
                        reservationDao.deleteSpecificReservation(String.valueOf(slotThatIsGoingToBeFree.getNumber()));
                        vehicleDao.deleteSpecificVehicle(vehicleSimulatedThatIsGoingToBeRemoved.getVehicleLicense());
                    }
                }


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

    }

    public void stopInteger (){
        this.i = 0;
    }

    public void resumInteger(){
        this.i = 1;
    }
}
