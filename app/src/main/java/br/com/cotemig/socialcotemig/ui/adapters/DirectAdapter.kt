package br.com.cotemig.socialcotemig.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.Message

class DirectAdapter(var context: Context, var username: String, var messages: List<Message>) :
    RecyclerView.Adapter<DirectAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        if (viewType == 0) {
            var view = LayoutInflater.from(context).inflate(R.layout.item_balloon_user, parent, false)
            return MessageViewHolder(view)
        } else {
            var view =
                LayoutInflater.from(context).inflate(R.layout.item_balloon_me, parent, false)
            return MessageViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (messages[position].user == username) {
            return 0
        } else {
            return 1
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(message: Message) {

            var msg = itemView.findViewById<TextView>(R.id.message)
            msg.text = message.message

        }

    }
}