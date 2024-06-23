package io.github.tundeadetunji;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;

import java.util.Collections;
import java.util.List;

public class FxLite {

    private static final String CONTROL_NOT_SUPPORTED = "Control is not supported!";
    private static final String EMPTY_TEXT = "";

    public static String content(TextField textField) {
        return textField != null ? textField.getText() : EMPTY_TEXT ;
    }

    public static boolean content(CheckBox checkBox) {
        return checkBox != null && checkBox.isSelected();
    }

    public static <T> T content(ChoiceBox<T> choiceBox) {
        return choiceBox != null ? choiceBox.getValue() : (T) EMPTY_TEXT;
    }

    public static <T> T content(ComboBox<T> combo) {
        return combo != null ? combo.getValue() : (T) EMPTY_TEXT;
    }

    public static <T> T content(ListView<T> listView) {
        if (listView == null) return (T) EMPTY_TEXT;
        return listView.getSelectionModel().getSelectionMode() == SelectionMode.SINGLE ?
                listView.getSelectionModel().getSelectedItem() :
                listView.getSelectionModel().getSelectedItems().get(0);
    }

    public static void bindProperty(ComboBox<String> combo, List<String> items, boolean selectFirstItem, String promptText, boolean sort) {
        if (combo == null) return;

        if (sort) {
            try {
                Collections.sort(items);
            } catch (UnsupportedOperationException ignored) {
            }
        }

        for (String item : items) {
            combo.getItems().add(item);
        }
        if (promptText != null) combo.setPromptText(promptText);
        if (selectFirstItem) combo.setValue(items.get(0));

        //combo.setEditable(true);
    }
    public static void bindProperty(ComboBox<String> combo, List<String> items, boolean selectFirstItem, String promptText, boolean sort, ChangeListener<String> listener) {
        bindProperty(combo, items, selectFirstItem, promptText, sort);
        if (listener != null) combo.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public static void bindProperty(ComboBox<String> combo, List<String> items, boolean selectFirstItem, String promptText, boolean sort, boolean editable) {
        if (combo == null) return;

        if (sort) {
            try {
                Collections.sort(items);
            } catch (UnsupportedOperationException ignored) {
            }
        }

        for (String item : items) {
            combo.getItems().add(item);
        }
        if (promptText != null) combo.setPromptText(promptText);
        if (selectFirstItem) combo.setValue(items.get(0));

        combo.setEditable(editable);
    }

    public static void bindProperty(ComboBox<String> combo, List<String> items, boolean selectFirstItem, String promptText, boolean sort, boolean editable, ChangeListener<String> listener) {
        bindProperty(combo, items, selectFirstItem, promptText, sort, editable);
        if (listener != null) combo.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public static void bindProperty(ListView<String> listView, List<String> items, boolean sort) {
        if (listView == null) return;

        listView.getSelectionModel()
                .setSelectionMode(
                        SelectionMode.SINGLE
                );

        if (sort) {
            try {
                Collections.sort(items);
            } catch (UnsupportedOperationException ignored) {
            }
        }

        for (String item : items) {
            listView.getItems().add(item);
        }
    }

    public static void bindProperty(ListView<String> listView, List<String> items, boolean sort, ChangeListener<String> listener) {
        bindProperty(listView,items, sort);
        if (listener != null) listView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public static void check(CheckBox checkBox) {
        try{
            checkBox.setSelected(true);
        }
        catch (Exception ignored){
        }
    }

    public static void unCheck(CheckBox checkBox) {
        try{
            checkBox.setSelected(false);
        }
        catch (Exception ignored){
        }
    }

    public static void check(CheckBox checkBox, boolean value) {
        try{
            checkBox.setSelected(value);
        }
        catch (Exception ignored){
        }
    }

}
