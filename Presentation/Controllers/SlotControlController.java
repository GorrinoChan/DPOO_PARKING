package Presentation.Controllers;
import Presentation.Views.*;
public class SlotControlController {
    private SlotControlView slotControlView;

    public SlotControlController(SlotControlView slotControlView) {
        this.slotControlView = slotControlView;
        slotControlView.getReturnButton().addActionListener(e -> returnToMenu());
        slotControlView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        slotControlView.getreserveSlotButton().addActionListener(e -> openReserveSlotView());
        slotControlView.getremoveReservationButton().addActionListener(e-> openRemoveReservationView());
    }

    private void openUserProfileView() {
        slotControlView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        slotControlView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
    }

    private void openReserveSlotView() {
        slotControlView.dispose();
        ReserveSlotView reserveSlotView = new ReserveSlotView();
        new ReserveSlotController(reserveSlotView);
        reserveSlotView.setVisible(true);
    }

    private void openRemoveReservationView() {
        slotControlView.dispose();
        RemoveReservationView removeReservationView = new RemoveReservationView();
        new RemoveReservationController(removeReservationView);
        removeReservationView.setVisible(true);
    }
}