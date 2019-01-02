---
title: Thoughts about electronic mail
section: handouts
---
# {{ page.title }}

*I end up talking to students about email more than they or I would like.
Here are a few quick notes about ways in which I use (and do not use) email.*

Choosing subject lines
----------------------

I get way too much email, literally hundreds of messages each day.  I rely
on programs to automatically file my email and to search my email.  I also
scan email to find high-priority.  Hence, I'd appreciate it if you'd help me
process email from you more carefully.  Here are a few short guidelines.

* Please take the time to choose an appropriate subject line for your 
  email message.
* If I tell you to use a particular subject line for an email message,
  particularly for an assignment, please use that subject line.
  The correct subject makes it easier for me to file your email
  and, more importantly, for me to *find* your email.
  The typical subject line is "{{ site.courseid }}  *Assignment*: *Title*
  (*Your Names*)".  For example, in submitting the typical
  introductory survey, I might use the subject line "&courseid;
  Assignment 1: Introductory Survey (Samuel A. Rebelsky)" or
  the subject line "{{ site.courseid }} Assignment 1: Introductory Survey
  (Rebelsky)".
* If you are asking for help on an assignment, please do not use a
  title like "Homework assignment".  I generally assume
  that titles like that represent *submissions*
  of homework assignments, and may file them without looking at them
  until it comes time to grade the assignment.  If you're asking a
  question, please put a term like "Question" or
  "Help" or even "**HELP!**" in the subject
  line.  (Yes, I know that the use of all capital letters is a form
  of shouting.  It's okay, and perhaps even preferable, if you virtually 
  shout when asking for help.)
* If you are replying to a message and are changing the topic of
  the message, please also change the subject line.  If you want to
  be precise, you can include the old subject line in parentheses,
  prefixed by the word "Was", as in "Question on
  the assignment (was re: This week's convocation)".

Reasonable delays
-----------------

I do my best to respond to email within twenty-four hours.  If I do not
do so, you should feel free to email me a reminder.  If you have an urgent
question, please send it via email with "**Urgent**" in the title.  If I
do not respond quickly enough, you can feel free to text me.  If you do 
text me, please include your name.  And don't text without sending email
first.

Authentication and encryption
-----------------------------

Most of us, myself included, don't think much about email, other
than that it's a convenient way to communicate.  But a few recent 
events suggest that we should be a bit more thoughtful.  Hence,
I encourage you to consider using mail encryption software,
particularly the <ulink url="https://www.gnupg.org/">GNU Privacy 
Guard (GPG)</ulink> and the more general Pretty Good Privacy (PGP)
protocol.

Since I use GPG, I've written <ulink
url="https://www.cs.grinnell.edu/~rebelsky/gpg.html">a really brief
introduction</ulink>.  There are a host of more useful and more
comprehensive introductions to and tutorials about GPG, so I'm not
going to write much here, other than to recommend that you learn
about using GPG.  I will say that, depending on what email package
I'm using and who I'm communicating with, encryption is either trivial
(one click and one password when I'm using Mail.app on the Macintosh
and sending email) or a slight pain (saving the encrypted message as
a file and then running a decryption program when I'm receiving an
encrypted message via Webmail).

GPG is available for all three major operating systems (and probably
for others, too).

* On the Mac, I use [GPG Suite](https://gpgtools.org/).
  I find it simple and straightforward.  Most of my colleagues use
  it too.
* I don't use Microsoft Windows, but I hear that 
  [gpg4win](https://www.gpg4win.org/)
  works pretty well.
* On the Linux workstations in the MathLAN, GPG is installed as
  a command line tool.

If you send me your public key (or a link to your public key) and
ask me to encrypt email to you, I will do my best to remember
(although I may not always succeed).   You can find my public key
at <https://www.cs.grinnell.edu/~rebelsky/rebelsky.gpg>.
or at the MIT key server, <https://pgp.mit.edu>.
