package com.devrim.selfemployed.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.devrim.selfemployed.R;
import com.devrim.selfemployed.database.Project;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<Project> projects = new ArrayList<>();
    private int selectedPosition;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false));
        viewHolder.projectName.setOnClickListener(v -> {
            final int previousPosition = selectedPosition;
            selectedPosition = viewHolder.getAdapterPosition();
            notifyItemChanged(viewHolder.getAdapterPosition());
            if (previousPosition != -1) {
                notifyItemChanged(previousPosition);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.projectName.setText(projects.get(position).name);
        holder.projectName.setActivated(selectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    @Nullable
    public Project getSelectedProject() {
        if (selectedPosition != -1) {
            return projects.get(selectedPosition);
        }
        return null;
    }

    public void setProjects(final List<Project> projects) {
        this.projects = projects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.project_name)
        TextView projectName;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
