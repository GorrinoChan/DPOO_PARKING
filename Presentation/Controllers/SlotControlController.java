package Presentation.Controllers;
import Presentation.Views.*;
public class SlotControlController {
    private SlotControlView slotControlView;
    private UserMenuView userMenuView;

    public SlotControlController(SlotControlView slotControlView, UserMenuView userMenuView) {
        this.slotControlView = slotControlView;
        this.userMenuView = userMenuView;
        slotControlView.getReturnButton().addActionListener(e -> returnToMenu());
        slotControlView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        slotControlView.getReserveSlotButton().addActionListener(e -> openReserveSlotView());
        slotControlView.getRemoveReservationButton().addActionListener(e-> openRemoveReservationView());
        slotControlView.getReservationsButton().addActionListener(e -> openSlotReservationsView());
    }

    private void openUserProfileView() {
        slotControlView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        slotControlView.dispose();
        userMenuView.setVisible(true);
    }

    private void openReserveSlotView() {
        slotControlView.dispose();
        ReserveSlotView reserveSlotView = new ReserveSlotView();
        new ReserveSlotController(reserveSlotView, userMenuView);
        reserveSlotView.setVisible(true);
    }

    private void openRemoveReservationView() {
        slotControlView.dispose();
        RemoveReservationView removeReservationView = new RemoveReservationView();
        new RemoveReservationController(removeReservationView, userMenuView);
        removeReservationView.setVisible(true);
    }

    private void openSlotReservationsView() {
        slotControlView.dispose();
        SlotReservationsView slotReservationsView = new SlotReservationsView();
        new SlotReservationsController(slotReservationsView, userMenuView);
        slotReservationsView.setVisible(true);
    }
}