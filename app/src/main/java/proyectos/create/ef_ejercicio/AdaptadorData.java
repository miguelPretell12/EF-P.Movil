package proyectos.create.ef_ejercicio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorData extends RecyclerView.Adapter<AdaptadorData.UsuarioViewHolder> {
    Context context;
    List<Usuario> listausuario;

    public AdaptadorData(Context context, List<Usuario> listausuario) {
        this.context = context;
        this.listausuario = listausuario;
    }
    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, null, false);
        return new AdaptadorData.UsuarioViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.tvtNombre.setText(listausuario.get(position).nombre);
        holder.tvtDni.setText(listausuario.get(position).dni);
        holder.tvtTelefono.setText(listausuario.get(position).telefono);
        holder.tvtCorreo.setText(listausuario.get(position).correo);
    }

    @Override
    public int getItemCount() {
        return listausuario.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView tvtNombre, tvtDni, tvtTelefono, tvtCorreo;

        public UsuarioViewHolder(@NonNull View itemView){
            super(itemView);

            tvtNombre = itemView.findViewById(R.id.tvtNombre);
            tvtDni = itemView.findViewById(R.id.tvtDni);
            tvtTelefono = itemView.findViewById(R.id.tvtTelefono);
            tvtCorreo = itemView.findViewById(R.id.tvtCorreo);

        }
    }
}

