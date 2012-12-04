#! /usr/bin/perl
($username, $pass) = @ARGV;
open (FH, "/etc/shadow") or die;
while (<FH>) { last if (/^$username/); }
exit 2 unless $_;
(undef, $hash) = split(':');
(undef,$method,$salt) = split('\$',$hash);
exit (($hash eq crypt($pass,"\$" . $method . "\$" . $salt)) ? 0 : 1);

