package com.gremzor.personpopulatorpro.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gremzor.personpopulatorpro.R;
import com.gremzor.personpopulatorpro.dao.PersonDAO;
import com.gremzor.personpopulatorpro.model.Person;

import java.text.DateFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Standard ArrayAdapter that allows the list of persons to be displayed in a list view, uses a
 * view holder.
 */

public class PersonAdapter extends ArrayAdapter<Person>{

    @Inject
    PersonDAO personDAO;

    static class ViewHolder {
        @Bind(R.id.person_item_name) TextView name;
        @Bind(R.id.person_item_dob) TextView dob;
        @Bind(R.id.person_item_zip) TextView zip;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public PersonAdapter(Context context, List<Person> persons) {
        super(context, R.layout.person_item, persons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.person_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Person person = getItem(position);

        holder.name.setText(person.getLastName() + ", " + person.getFirstName());
        holder.dob.setText(DateFormat.getDateInstance().format(person.getDOB()));
        holder.zip.setText(person.getZip());

        return convertView;
    }
}
