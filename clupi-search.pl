#!/usr/bin/perl
use Search::Elasticsearch;
use Data::Dumper;
use Text::Table;
use File::Basename;

sub cleanDur {
	(my $p) = @_;
	$p =~ s/ \(approx\)//;
	$p;
}

sub cleanPath {
	(my $p) = @_;
	$p =~ s/\/Volumes\/v2\///;
	$p;
}

my $e = Search::Elasticsearch->new(
	nodes => [
		"endor.sensei.ath.cx/search" 
	]
);

(my $query,my $from,my $size) = @ARGV;

if(!$from) {
	$from = 0;
}

if(!$size) {
	$size = 10;
}

my $result = $e->search(
	index 	=> 'media',
	fields	=> ['Artist','Title','AudioBitrate','SourceFile','AvgBitrate','Duration'],
	body	=> {
		from	=> $from,
		size	=> $size,
		query	=> {
			bool	=> {
				should	=> [
					{ match => {Artist => $query}},
					{ match => {Title => $query}},
					{ match => {SourceFile => $query}},
				]
			}
		}
	}
);

#print Dumper($result->{'hits'}->{'hits'});

my $rb = Text::Table->new(
	"Artist","Title","Bitrate","Length","Duration"
);

die "no results" unless $result;
my @hits = @{$result->{hits}->{hits}};
#print Dumper(@hits);
foreach $hit(@hits){
	my $bitrate = $hit->{fields}->{AudioBitrate}[0] 
			? $hit->{fields}->{AudioBitrate}[0]
			: $hit->{fields}->{AvgBitrate}[0];
	$rb->add(
		$hit->{fields}->{Artist}[0],
		$hit->{fields}->{Title}[0],
		$bitrate,
		(cleanDur $hit->{fields}->{Duration}[0]),
		(cleanPath $hit->{fields}->{SourceFile}[0]),
		#basename($hit->{fields}->{SourceFile}[0]),
	);
}
print $rb;
