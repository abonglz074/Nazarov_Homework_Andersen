package com.mytestprogram.homework_6

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mytestprogram.homework_6.databinding.RecycleSingleItemBinding
import com.mytestprogram.homework_6.models.Contact

//Список действий, который может выполнять пользователь с элементом списка
interface ContactActionListener {

    fun onContactDelete(contact: Contact)

    fun onContactDetails(contact: Contact)
}

class ContactAdapter(
    private val actionListener: ContactActionListener
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(), View.OnClickListener, View.OnLongClickListener {

    var contacts: List<Contact> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onClick(v: View) {
        val contact = v.tag as Contact //Вытягиваем контакт из тэга
        actionListener.onContactDetails(contact)

    }

    override fun onLongClick(v: View): Boolean {

        val contact = v.tag as Contact
        AlertDialog.Builder(v.context)
            .setTitle("Удаление пользователя")
            .setMessage("Хотите удалить пользователя?")
            .setPositiveButton("Да") { _, _ ->
                actionListener.onContactDelete(contact)
            }
            .setNegativeButton("Нет") { dialog, _ -> dialog.dismiss() }
            .create().show()
        return true

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecycleSingleItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.root.setOnLongClickListener(this)

        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        with(holder.binding) {
            holder.itemView.tag = contact
            contactName.text = contact.name
            contactSurname.text = contact.surname
            contactPhone.text = contact.phone
            Glide.with(photoListContact.context)
                .load(contact.photo)
                .circleCrop()
                .into(photoListContact)
        }
    }

    override fun getItemCount(): Int = contacts.size

    class ContactViewHolder(val binding: RecycleSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}