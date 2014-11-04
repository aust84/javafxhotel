package edittable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class EditingCell<E> extends TableCell<Object, String>{
	
	private TextField text;
	
	public EditingCell(){
		
	}
	
	@Override
	public void startEdit(){
		
		if (!isEmpty()){
			super.startEdit();
			CreateTextField();
			setText(null);
			setGraphic(text);
			text.selectAll();
		}
		
		
	}
	@Override
	public void cancelEdit(){
		
		super.cancelEdit();
		setText((String) getItem());
		setGraphic(null);
	}
	
    
	public void updateItem(String item, boolean empty){
		
		super.updateItem(item, empty);
		
		if(empty){
			setText(null);
			setGraphic(null);
		}else{
			
			if(isEditing()){
				if(text != null){
					text.setText(getString());
				}
				setText(null);
				setGraphic(text);
			}else{
				setText(getString());
				setGraphic(null);
			}
			
		}
	}
	
	private void CreateTextField(){
		
		text= new TextField(getString());
		text.setMinWidth(this.getWidth()-this.getGraphicTextGap() *2);
		
		text.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2){
				if(!arg2){
					commitEdit(text.getText());
				}
			}
		});
	}
	
	public String getString(){
		return getItem() == null ? "" : getItem().toString();
	}
}